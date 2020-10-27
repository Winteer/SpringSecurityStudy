package com.winter.security.filter;

import com.winter.security.model.UserInfo;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName : AclInterceptor
 * @Description : ACL访问控制
 * @Author : Winter
 * @Date: 2020-10-21 09:47
 */
@Component
public class AclInterceptor extends HandlerInterceptorAdapter {
    //    private String[] permitUrls = new String[]{"/users/login","/users/logout", "/doc.html", "/webjars/js/app.b299109b.js", "/webjars/css/app.ba351dc1.css", "/webjars/js/chunk-vendors.9ff0f717.js"};
    private String[] permitUrls = new String[]{"/users/login", "/users/logout"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("访问控制 -- 4");
        boolean result = true;
        if (!ArrayUtils.contains(permitUrls, request.getRequestURI())) {

            UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
            //根据用户信息来
            if (userInfo == null) {
                response.setContentType("text/plain");
                response.getWriter().write("need authentication!");
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                result = false;

            } else {
                //根据方法来
                String method = request.getMethod();
                if (!userInfo.hasPermission(method)) {
                    response.setContentType("text/plain");
                    response.getWriter().write("forbidden!");
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    result = false;
                }
            }
        }
        return result;
    }
}
