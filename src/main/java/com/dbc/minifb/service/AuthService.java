package com.dbc.minifb.service;

import com.dbc.minifb.business.constant.Code;
import com.dbc.minifb.business.response.BaseResponse;
import com.dbc.minifb.business.response.ResponseFactory;
import com.dbc.minifb.business.vo.request.LoginVO;
import com.dbc.minifb.business.vo.request.RegisterVO;
import com.dbc.minifb.entity.User;
import com.dbc.minifb.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author: Knox
 * @Date: 2018/12/3 7:02 PM
 * @Description: You Know
 * @Version 1.0
 */
@Service
public class AuthService {
    @Resource
    private UserRepository userRepository;
    private static final String defaultHeadImage = "/static/images/headicons/default.jpeg";

    @Transactional
    public BaseResponse login(LoginVO loginVO, HttpSession session) {
        String username = loginVO.getUsername();
        String password = loginVO.getPassword();
        List<User> users = userRepository.findByUsernameEquals(username);
        if (ObjectUtils.isEmpty(users)) {
            return ResponseFactory.response(Code.USERNAME_OR_PSW_ERROR);
        }
        User user = users.get(0);
        if (!password.equals(user.getPassword())) {
            return ResponseFactory.response(Code.USERNAME_OR_PSW_ERROR);
        }
        session.setAttribute("user", user);
        return ResponseFactory.response(Code.SUCCESS, user);
    }

    @Transactional
    public BaseResponse register(RegisterVO registerVO) {
        String username = registerVO.getUsername();
        String password = registerVO.getPassword();
        Integer age = registerVO.getAge();
        Integer sex = registerVO.getSex();
        String phoneNumber = registerVO.getPhoneNumber();
        if (ObjectUtils.isEmpty(username) || ObjectUtils.isEmpty(password) || ObjectUtils.isEmpty(age) || ObjectUtils.isEmpty(sex) || ObjectUtils.isEmpty(phoneNumber)) {
            return ResponseFactory.response(Code.USER_INFO_NOT_COMPLETE);
        }
        List<User> users = userRepository.findByUsernameEquals(username);
        if (!ObjectUtils.isEmpty(users)) {
            return ResponseFactory.response(Code.USERNAME_ALREADY_EXISTS);
        }
        User user = new User(username, password, sex, phoneNumber, age, new Date());
        //TODO 默认头像
        user.setHeadIconUrl(defaultHeadImage);
        userRepository.save(user);
        return ResponseFactory.response(Code.SUCCESS, user);

    }


}
