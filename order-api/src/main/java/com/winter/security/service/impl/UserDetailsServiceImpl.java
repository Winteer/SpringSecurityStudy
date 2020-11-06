//package com.winter.security.service.impl;
//
//import com.winter.security.model.User;
//import com.winter.security.service.UserDetailsService;
//import org.apache.log4j.Logger;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
// //删除订单服务安全相关代码
///**
// * @ClassName : UserDetailsServiceImpl
// * @Description : UserDetailsService实现类
// * @Author : Winter
// * @Date: 2020-10-29 14:47
// */
//@Component
//public class UserDetailsServiceImpl implements UserDetailsService {
//    private Logger logger = Logger.getLogger(this.getClass());
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        logger.info("Order Application  username : " + username);
//        User user = new User();
//        user.setUsername(username);
//        user.setId(1L);
//        return user;
//    }
//}
