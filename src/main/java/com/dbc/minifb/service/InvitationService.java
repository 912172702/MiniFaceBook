package com.dbc.minifb.service;

import com.dbc.minifb.business.constant.Code;
import com.dbc.minifb.business.response.BaseResponse;
import com.dbc.minifb.business.response.ResponseFactory;
import com.dbc.minifb.business.vo.response.ResponseInvitationVO;
import com.dbc.minifb.entity.Comment;
import com.dbc.minifb.entity.Invitation;
import com.dbc.minifb.entity.User;
import com.dbc.minifb.repository.InvitationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: Knox
 * @Date: 2018/12/3 6:59 PM
 * @Description: You Know
 * @Version 1.0
 */
@Service
public class InvitationService {

    @Resource
    private InvitationRepository invitationRepository;
    @Resource
    private CommentService commentService;
    @Resource
    private UserService userService;
    @Resource
    private FileOptionService fileOptionService;

    @Transactional
    public void save(Invitation invitation) {
        invitationRepository.save(invitation);
    }

    @Transactional
    public Invitation getById(Long id) {
        return invitationRepository.findById(id).get();
    }

    @Transactional
    public List<ResponseInvitationVO> getPostsByUserId(Long userId) {
        User user = userService.getUserById(userId);
        List<ResponseInvitationVO> responseInvitationVOList = new ArrayList<>();
        List<Invitation> invitations = invitationRepository.findInvitationsByPostByOrderByTimeDesc(user.getId());
        invitations.forEach(invitation -> {
            //这里如果不设置user，获取到的invitaton中的user为空
            invitation.setPostBy(user);
            List<Comment> comments = commentService.getCommentsByInvitation(invitation);
            ResponseInvitationVO vo = new ResponseInvitationVO();
            vo.setInvitation(invitation);
            vo.setComments(comments);
            responseInvitationVOList.add(vo);
        });
        return responseInvitationVOList;
    }

    @Transactional
    public BaseResponse getAllPostsMySubscribe(Long myId) {
        User my = userService.getUserById(myId);
        List<ResponseInvitationVO> responseInvitationVOList = new ArrayList<>();
        my.getSubscribes().forEach(user -> {
            List<ResponseInvitationVO> postsByUser = getPostsByUser(user);
            responseInvitationVOList.addAll(postsByUser);
        });
        //把我自己的放进去
        responseInvitationVOList.addAll(getPostsByUser(my));
        //按照时间逆序排序
        responseInvitationVOList.sort((a, b) -> b.getInvitation().getTime().compareTo(a.getInvitation().getTime()));
        return ResponseFactory.response(Code.SUCCESS, responseInvitationVOList);
    }

    @Transactional
    public List<ResponseInvitationVO> getPostsByUser(User user) {
        return getPostsByUserId(user.getId());
    }

    @Transactional
    public BaseResponse deleteById(Long postId) {
        invitationRepository.deleteById(postId);
        return ResponseFactory.response(Code.SUCCESS);
    }

    @Transactional
    public BaseResponse postInvitation(String content, MultipartFile[] files, Long userId) {
        User user = userService.getUserById(userId);
        List<String> imagesPaths = fileOptionService.uploadPostImages(files, user);
        Invitation invitation = new Invitation();
        invitation.setPostBy(user);
        invitation.setTime(new Date());
        invitation.setContent(content);
        invitation.getImagesUrl().addAll(imagesPaths);
        invitationRepository.save(invitation);
        return ResponseFactory.response(Code.SUCCESS);
    }
}
