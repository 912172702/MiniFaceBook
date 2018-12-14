package com.dbc.minifb.service;

import com.dbc.minifb.business.constant.Code;
import com.dbc.minifb.business.response.BaseResponse;
import com.dbc.minifb.business.response.ResponseFactory;
import com.dbc.minifb.entity.User;
import com.dbc.minifb.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @Author: Knox
 * @Date: 2018/12/2 12:16 AM
 * @Description: You Know
 * @Version 1.0
 */
@Service
public class UserService {
    @Resource
    private UserRepository userRepository;
    @Resource
    private FileOptionService fileOptionService;

    @Transactional
    public BaseResponse subscribe(Long myId, Long otherId) {
        User my = getUserById(myId);
        User other = getUserById(otherId);
        my.getSubscribes().add(other);
        userRepository.save(my);
        return ResponseFactory.response(Code.SUCCESS);
    }

    @Transactional
    public BaseResponse unsubscribe(Long myId, Long otherId) {
        User my = getUserById(myId);
        User other = getUserById(otherId);
        my.getSubscribes().remove(other);
        userRepository.save(my);
        return ResponseFactory.response(Code.SUCCESS);
    }

    @Transactional
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Transactional
    public BaseResponse findUserByUsernameLike(String like) {
        return ResponseFactory.response(Code.SUCCESS, userRepository.findByUsernameLike(".*" + like + ".*"));
    }

    @Transactional
    public BaseResponse updateHeadIcon(MultipartFile image, Long userId) {
        User user = getUserById(userId);
        user.setHeadIconUrl(fileOptionService.uploadHeadIcon(image, user));
        userRepository.save(user);
        return ResponseFactory.response(Code.SUCCESS, user);
    }

    @Transactional
    public BaseResponse getAllMySubscribeUser(Long userId) {
        User user = getUserById(userId);
        return ResponseFactory.response(Code.SUCCESS, user.getSubscribes());
    }

    @Transactional
    public BaseResponse deleteUserById(Long userId) {
        userRepository.deleteById(userId);
        return ResponseFactory.response(Code.SUCCESS);
    }
}
