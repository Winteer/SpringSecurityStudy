package com.winter.security.config;


import com.winter.security.filter.AuditLogInterceptor;
import com.winter.security.filter.AclInterceptor;
import com.winter.security.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Optional;

/**
 * @ClassName : SecurityConfig
 * @Description :
 * @Author : Winter
 * @Date: 2020-10-20 10:17
 */
@Configuration
@EnableJpaAuditing //总开关，审计功能
public class SecurityConfig implements WebMvcConfigurer {

    @Autowired
    private AuditLogInterceptor auditLogInterceptor;
    @Autowired
    private AclInterceptor aclInterceptor;


    //配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(auditLogInterceptor);
        registry.addInterceptor(aclInterceptor);
    }


    //
    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAware<String>() {
            @Override
            public Optional<String> getCurrentAuditor() {

                //Spring 提供的静态方法获取Session，拿到当前的用户名
                ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                UserInfo userInfo = (UserInfo) servletRequestAttributes.getRequest().getSession().getAttribute("user");
                String username = null;
                if (userInfo != null) {
                    username = userInfo.getUsername();
                }
                return Optional.ofNullable(username);
            }
        };
    }
}
