package com.winter.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * @ClassName : OAuth2AuthServerConfig
 * @Description : 认证服务器配置
 * @Author : Winter
 * @Date: 2020-10-27 11:45
 */

@Configuration
@EnableAuthorizationServer // 表示当前应用作为认证授权的服务器来存在
public class OAuth2AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DataSource dataSource;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //检查token的这个服务的访问规则
        security.checkTokenAccess("isAuthenticated()");
    }

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    //数据库配置
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
    }

//内存写死
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                .withClient("orderApp")
//                .secret(passwordEncoder.encode("123456")) //用passwordEncoder来加密，它本质上就是包装了我们之前介绍的工具。用一个随即的盐来做加密。把这个盐也编译到加密后的密文里面去。
//                .scopes("read", "write")    //scopes后面可以用来做ACL的权限控制,为了简便这里就写两个read和write
//                .accessTokenValiditySeconds(3600)   //令牌的有效期
//                .resourceIds("order-server")   //resourceIds代表资源服务器的id,表示orderApp可以访问order-server这个资源服务器。
//                .authorizedGrantTypes("password")  //授权方式，Oauth协议有四种授权类型
//                .and()
//                .withClient("orderService")
//                .secret(passwordEncoder.encode("123456")) //用passwordEncoder来加密，它本质上就是包装了我们之前介绍的工具。用一个随即的盐来做加密。把这个盐也编译到加密后的密文里面去。
//                .scopes("read")    //scopes后面可以用来做ACL的权限控制,为了简便这里就写两个read和write
//                .accessTokenValiditySeconds(3600)   //令牌的有效期
//                .resourceIds("order-server")   //resourceIds代表资源服务器的id,表示orderApp可以访问order-server这个资源服务器。
//                .authorizedGrantTypes("password");  //授权方式，Oauth协议有四种授权类型
//
////        super.configure(clients);
//    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.authenticationManager(authenticationManager);
        endpoints
                .tokenStore(tokenStore()) //告诉服务器用这个tokenStore来存取token,token会存到数据库内。
                .authenticationManager(authenticationManager);
    }
}
