package com.winter.security.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @ClassName : AuditLogFilter
 * @Description : 审计过滤器
 * @Author : Winter
 * @Date: 2020-11-05 10:08
 */
@Component
public class AuditLogFilter extends ZuulFilter {

    private Logger logger = Logger.getLogger(this.getClass());

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        logger.info("audit log insert!");
        return null;
    }
}
