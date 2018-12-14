package com.dbc.minifb.controller;

import com.dbc.minifb.business.constant.Code;
import com.dbc.minifb.business.response.BaseResponse;
import com.dbc.minifb.business.response.ResponseFactory;
import com.dbc.minifb.business.vo.request.CommentVO;
import com.dbc.minifb.business.vo.response.ResponseInvitationVO;
import com.dbc.minifb.service.CommentService;
import com.dbc.minifb.service.FileOptionService;
import com.dbc.minifb.service.InvitationService;
import com.dbc.minifb.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;


/**
 * @Author: Knox
 * @Date: 2018/12/2 12:17 AM
 * @Description: You Know
 * @Version 1.0
 */
@Api(description = "所有其他功能性业务接口")
@RestController
@RequestMapping("/MiniFB/v1")
public class BusinessController {
    @Resource
    private UserService userService;
    @Resource
    private CommentService commentService;
    @Resource
    private InvitationService invitationService;


    @ApiOperation(value = "subscribe", notes = "关注某人", response = BaseResponse.class)
    @PostMapping("/subscribe/{myId}/{otherId}")
    public BaseResponse subscribe(
            @ApiParam(value = "我的用户ID") @PathVariable String myId,
            @ApiParam(value = "对方的用户ID") @PathVariable String otherId
    ) {
        return userService.subscribe(Long.parseLong(myId), Long.parseLong(otherId));
    }

    @ApiOperation(value = "unsubscribe", notes = "取关某人", response = BaseResponse.class)
    @PostMapping("/unsubscribe/{myId}/{otherId}")
    public BaseResponse unsubscribe(
            @ApiParam(value = "我的用户ID") @PathVariable String myId,
            @ApiParam(value = "对方的用户ID") @PathVariable String otherId
    ) {
        return userService.unsubscribe(Long.parseLong(myId), Long.parseLong(otherId));

    }

    @ApiOperation(value = "getAllMySubscribeUser", notes = "获取所有关注的用户", response = BaseResponse.class)
    @GetMapping("/subscribe/all/{myId}")
    public BaseResponse getAllMySubscribeUser(@PathVariable("myId") String myId) {
        return userService.getAllMySubscribeUser(Long.parseLong(myId));
    }

    @ApiOperation(value = "postInvitation", notes = "发布动态", response = BaseResponse.class)
    @PostMapping(value = "/post/invitation/{myId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BaseResponse postInvitation(
            @ApiParam(value = "文字内容") @RequestParam("content") String content,
            @ApiParam(value = "我的用户ID") @PathVariable String myId,
            @ApiParam(value = "图片(可以多张)") @RequestPart("images") MultipartFile[] images
    ) {
        System.out.println(images.length);
        return invitationService.postInvitation(content, images, Long.parseLong(myId));
    }

    @ApiOperation(value = "getAllPostsMySubscribe", notes = "获取我关注用户的最新动态", response = BaseResponse.class)
    @GetMapping("/post/getAllSubscribe/{myId}")
    public BaseResponse getAllPostsMySubscribe(
            @ApiParam(value = "我的用户ID") @PathVariable String myId
    ) {
        return invitationService.getAllPostsMySubscribe(Long.parseLong(myId));
    }

    @ApiOperation(value = "getAllPosts", notes = "根据用户ID获取某人的所有动态", response = BaseResponse.class)
    @GetMapping("/post/getAll/{userId}")
    public BaseResponse getAllPosts(
            @ApiParam(value = "用户ID") @PathVariable String userId
    ) {
        List<ResponseInvitationVO> postsByUserId = invitationService.getPostsByUserId(Long.parseLong(userId));
        return ResponseFactory.response(Code.SUCCESS, postsByUserId);
    }

    @ApiOperation(value = "deletePost", notes = "根据动态ID删除动态", response = BaseResponse.class)
    @GetMapping("/post/delete/{postId}")
    public BaseResponse deletePost(
            @ApiParam(value = "动态ID") @PathVariable String postId
    ) {
        return invitationService.deleteById(Long.parseLong(postId));
    }

    @ApiOperation(value = "findUserNameLike", notes = "查找用户名包含某字符串的所有用户", response = BaseResponse.class)
    @GetMapping("/get/usernamelike/{like}")
    public BaseResponse findUserNameLike(
            @ApiParam(value = "包含的字符串") @PathVariable String like
    ) {
        return userService.findUserByUsernameLike(like);
    }

    @ApiOperation(value = "uploadHeadIcon", notes = "上传头像", response = BaseResponse.class)
    @PostMapping(value = "/upload/headIcon/{myId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BaseResponse uploadHeadIcon(
            @ApiParam(value = "我的用户ID") @PathVariable String myId,
            @ApiParam(value = "新的头像图片") @RequestPart("headIcon") MultipartFile headIcon
    ) {
        return userService.updateHeadIcon(headIcon, Long.parseLong(myId));
    }

    @ApiOperation(value = "comment", notes = "评论某个动态", response = BaseResponse.class)
    @PostMapping("/comment/{myId}/{postId}")
    public BaseResponse comment(
            @ApiParam(value = "评论内容") @RequestBody CommentVO commentVO,
            @ApiParam(value = "我的用户ID") @PathVariable String myId,
            @ApiParam(value = "动态ID") @PathVariable String postId
    ) {
        return commentService.comment(commentVO, Long.parseLong(myId), Long.parseLong(postId));
    }

}
