package com.winter.security.service.impl;

import com.lambdaworks.crypto.SCryptUtil;
import com.winter.security.model.User;
import com.winter.security.model.UserInfo;
import com.winter.security.repository.UserRepository;
import com.winter.security.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName : UserServiceImpl
 * @Description : 用户业务层实现
 * @Author : Winter
 * @Date: 2020-09-04 16:56
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserInfo save(UserInfo userInfo) {
        User user = new User();
        BeanUtils.copyProperties(userInfo, user);
        user.setPassword(SCryptUtil.scrypt(user.getPassword(), 32768, 8, 1));
        userRepository.save(user);
        return userInfo;
    }

    @Override
    public UserInfo update(UserInfo userInfo) {
        User user = new User();
        BeanUtils.copyProperties(userInfo, user);
        return null;
    }

    @Override
    public void delete(Long id) {


    }

    @Override
    public UserInfo find(Long id) {
        return userRepository.findById(id).get().buildInfo();
    }

    @Override
    public List<User> findByName(String name) {
        List<User> userInfoList = userRepository.findByName(name);
        return userInfoList;
    }

    @Override
    public UserInfo login(UserInfo userInfo) {
        UserInfo result = null;
        User user = userRepository.findByUsername(userInfo.getUsername());
        if (user != null && SCryptUtil.check(userInfo.getPassword(), user.getPassword())) {
            result = user.buildInfo();
        }
        return result;
    }
}
