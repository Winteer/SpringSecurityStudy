package com.winter.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @ClassName : OAuth2ResourceServerConfig
 * @Description : 资源服务器配置
 * @Author : Winter
 * @Date: 2020-10-28 09:30
 */
@Configuration
@EnableResourceServer //上EnableResourceServer的注解后，所有发往order-api服务器的所有请求，它都会去你的头里面找Token。如果找不到token就不让你过。就是这样一个逻辑
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {
    //配置HttpSecurity相关
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 配置 只有write的Scope可以调用POST请求，read的Scope调用GET请求
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST).access("#oauth2.hasAnyScope('write')")
                .antMatchers(HttpMethod.GET).access("#oauth2.hasAnyScope('read')");
        //任何请求都需要身份认证
//        http.authorizeRequests().anyRequest().authenticated();
//        这个请求除外，其他的请求都需要身份认证
//        http.authorizeRequests().antMatchers("/excepet").permitAll()
//                .anyRequest().authenticated();
    }

    //配置资源服务器
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("order-server");
    }
}
