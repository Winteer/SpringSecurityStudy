package com.winter.security.filter;

import com.winter.security.log.AuditLog;
import com.winter.security.repository.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName : AuditLogInterceptor
 * @Description :
 * @Author : Winter
 * @Date: 2020-10-19 17:00
 */
@Component
public class AuditLogInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("审计 -- 3");
        AuditLog log = new AuditLog();
        log.setMethod(request.getMethod());
        log.setPath(request.getRequestURI());

        //可用 SecurityConfig - auditorAware() 填写用户名
        // 代替下面的写法
//        User user = (User) request.getAttribute("user");
//        if (user != null) {
//            log.setUsername(user.getUsername());
//        }
        auditLogRepository.save(log);

        request.setAttribute("auditLogId", log.getId());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Long auditLogId = (Long) request.getAttribute("auditLogId");
        AuditLog log = auditLogRepository.findById(auditLogId).get();
        log.setStatus(response.getStatus());
        auditLogRepository.save(log);
    }
}
