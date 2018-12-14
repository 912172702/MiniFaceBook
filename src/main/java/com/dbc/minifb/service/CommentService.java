package com.dbc.minifb.service;

import com.dbc.minifb.business.constant.Code;
import com.dbc.minifb.business.response.BaseResponse;
import com.dbc.minifb.business.response.ResponseFactory;
import com.dbc.minifb.business.vo.request.CommentVO;
import com.dbc.minifb.entity.Comment;
import com.dbc.minifb.entity.Invitation;
import com.dbc.minifb.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author: Knox
 * @Date: 2018/12/3 6:38 PM
 * @Description: You Know
 * @Version 1.0
 */
@Service
public class CommentService {

    @Resource
    private CommentRepository commentRepository;
    @Resource
    private InvitationService invitationService;
    @Resource
    private UserService userService;

    @Transactional
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Transactional
    public BaseResponse comment(CommentVO commentVO, Long myId, Long postId) {
        Comment comment = new Comment();
        comment.setContent(commentVO.getContent());
        comment.setTime(new Date());
        comment.setWriteBy(userService.getUserById(myId));
        comment.setBelongTo(invitationService.getById(postId));
        commentRepository.save(comment);
        return ResponseFactory.response(Code.SUCCESS);
    }

    @Transactional
    public List<Comment> getCommentsByInvitation(Invitation invitation) {
        List<Comment> comments = commentRepository.findByBelongToOrderByTime(invitation.getId());
        comments.forEach(comment -> {
            comment.setWriteBy(commentRepository.findUserWrittenComment(comment.getId()));
        });
        return comments;
    }
}
