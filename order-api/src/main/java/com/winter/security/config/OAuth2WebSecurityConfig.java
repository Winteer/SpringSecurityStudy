//package com.winter.security.config;
//
//import com.winter.security.service.UserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
//import org.springframework.security.oauth2.provider.token.*;
//
// //删除订单服务安全相关代码
///**
// * @ClassName : OAuth2WebSecurityConfig
// * @Description : 验证令牌配置文件
// * @Author : Winter
// * @Date: 2020-10-28 09:47
// */
//@Configuration
//@EnableWebSecurity
//public class OAuth2WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    //远程token认证
//    @Bean
//    public ResourceServerTokenServices tokenServices() {
//        RemoteTokenServices tokenServices = new RemoteTokenServices();
//        tokenServices.setClientId("orderService");
//        tokenServices.setClientSecret("123456");
//        //校验服务的地址
//        tokenServices.setCheckTokenEndpointUrl("http://localhost:8884/oauth/check_token");
//        //修改下配置 ，让它用这个UserDetailsServiceImpl bean来读取相关的用户信息。
//        tokenServices.setAccessTokenConverter(getAccessTokenConverter());
//        return tokenServices;
//    }
//
//
//    //修改下配置 ，让它用这个UserDetailsServiceImpl bean来读取相关的用户信息。
//    private AccessTokenConverter getAccessTokenConverter() {
//        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
//        DefaultUserAuthenticationConverter userAuthenticationConverter = new DefaultUserAuthenticationConverter();
//        //设置用userDetailsService转换成user对象
//        userAuthenticationConverter.setUserDetailsService(userDetailsService);
//        accessTokenConverter.setUserTokenConverter(userAuthenticationConverter);
//        return accessTokenConverter;
//    }
//
//    @Bean   //暴露为一个Bean,覆写该方法。
//    @Override   //配置后。当资源服务器，也就是order-api拦截到请求里面的token时会调用这个方法来验证这个token
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        OAuth2AuthenticationManager authenticationManager = new OAuth2AuthenticationManager();
//        authenticationManager.setTokenServices(tokenServices());
//        return authenticationManager;
//    }
//}
