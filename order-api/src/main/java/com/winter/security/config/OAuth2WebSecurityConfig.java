package com.winter.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

/**
 * @ClassName : OAuth2WebSecurityConfig
 * @Description : 验证令牌配置文件
 * @Author : Winter
 * @Date: 2020-10-28 09:47
 */
@Configuration
@EnableWebSecurity
public class OAuth2WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //远程token认证
    @Bean
    public ResourceServerTokenServices tokenServices() {
        RemoteTokenServices tokenServices = new RemoteTokenServices();
        tokenServices.setClientId("orderService");
        tokenServices.setClientSecret("123456");
        //校验服务的地址
        tokenServices.setCheckTokenEndpointUrl("http://localhost:8884/oauth/check_token");
        return tokenServices;
    }

    @Bean   //暴露为一个Bean,覆写该方法。
    @Override   //配置后。当资源服务器，也就是order-api拦截到请求里面的token时会调用这个方法来验证这个token
    public AuthenticationManager authenticationManagerBean() throws Exception {
        OAuth2AuthenticationManager authenticationManager = new OAuth2AuthenticationManager();
        authenticationManager.setTokenServices(tokenServices());
        return authenticationManager;
    }
}
