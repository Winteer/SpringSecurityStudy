package com.winter.security.service.impl;

import com.winter.security.model.User;
import com.winter.security.service.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @ClassName : UserDetailsServiceImpl
 * @Description : UserDetailsService实现类
 * @Author : Winter
 * @Date: 2020-10-29 14:47
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        user.setUsername(username);
        user.setId(1L);
        return user;
    }
}
