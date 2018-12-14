package com.dbc.minifb.controller;

import com.dbc.minifb.business.response.BaseResponse;
import com.dbc.minifb.business.vo.request.LoginVO;
import com.dbc.minifb.business.vo.request.RegisterVO;
import com.dbc.minifb.service.AuthService;
import com.dbc.minifb.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @Author: Knox
 * @Date: 2018/12/3 7:01 PM
 * @Description: You Know
 * @Version 1.0
 */
@Api(description = "用户注册以及登录")
@RestController
@RequestMapping("/MiniFB/v1")
public class AuthController {
    @Resource
    private AuthService authService;
    @Resource
    private UserService userService;

    @ApiOperation(value = "register", notes = "用户注册", response = BaseResponse.class)
    @PostMapping("/register")
    public BaseResponse register(
            @ApiParam(value = "用户注册信息") @RequestBody RegisterVO registerVO
    ) {
        return authService.register(registerVO);
    }

    @ApiOperation(value = "login", notes = "用户注册", response = BaseResponse.class)
    @PostMapping("/login")
    public BaseResponse login(
            @ApiParam(value = "用户登录信息") @RequestBody LoginVO loginVO,
            HttpSession httpSession
    ) {
        return authService.login(loginVO, httpSession);
    }

    @ApiOperation(value = "deleteUserById", notes = "根据用户ID删除用户", response = BaseResponse.class)
    @PostMapping("/user/delete/{userId}")
    public BaseResponse deleteUserById(
            @ApiParam(value = "用户ID") @PathVariable String userId
    ) {
        return userService.deleteUserById(Long.parseLong(userId));
    }
}
