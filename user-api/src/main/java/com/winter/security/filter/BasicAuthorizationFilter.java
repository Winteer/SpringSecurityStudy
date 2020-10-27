package com.winter.security.filter;

import com.lambdaworks.crypto.SCryptUtil;
import com.winter.security.model.User;
import com.winter.security.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName : BasicAuthenticationFilter
 * @Description : 认证过滤器
 * @Author : Winter
 * @Date: 2020-10-14 11:39
 */
@Component
@Order(2)
public class BasicAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = httpServletRequest.getHeader("Authorization");
        System.out.println("认证 -- 2");
        if (StringUtils.isNotBlank(authHeader)) {
            String token64 = StringUtils.substringAfter(authHeader, "Basic ");
            String token = new String(Base64Utils.decodeFromString(token64));
            String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(token, ":");
            String username = items[0];
            String password = items[1];
            User user = userRepository.findByUsername(username);
            if (user != null && SCryptUtil.check(password, user.getPassword())) {
//                httpServletRequest.setAttribute("user", user);
                // 修改为 若采用basic方式，则每次都需要带Authorization头部信息
                httpServletRequest.getSession().setAttribute("user", user.buildInfo());
                httpServletRequest.getSession().setAttribute("temp", "yes");//通过basic方式产生的session为临时session
            }
        }
        //结束后 basic方式产生的session失效掉
        try {
            //try中的 是在请求之前调用的
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } finally {
            //是在请求之后调用的
            HttpSession session = httpServletRequest.getSession();
            if (session.getAttribute("temp") != null) {
                session.invalidate();
            }

        }
//        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
