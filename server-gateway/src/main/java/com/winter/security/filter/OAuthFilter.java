package com.winter.security.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.winter.security.model.TokenInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName : OAuthFilter
 * @Description :
 * @Author : Winter
 * @Date: 2020-11-04 09:55
 */
@Component
public class OAuthFilter extends ZuulFilter {

    private Logger logger = Logger.getLogger(this.getClass());

    @Override
    public String filterType() {
//        return "pre","post","error","route";
        //route是用来控制路由
        //pre就是在业务逻辑之前会执行过滤器里面的逻辑，也就是run方法里面的逻辑
        //post是在业务逻辑执行之后，执行run里面的逻辑
        //error是说在业务逻辑抛出异常之后，我们去会执行run里面的业务逻辑。
        return "pre";
    }

    //filterOrder控制过滤器的执行顺序
    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
        //判断过滤器是否起作用，返回true则代表永远起作用。
    }

    //run方法里面是真正的逻辑
    @Override
    public Object run() throws ZuulException {
        logger.info("OAuth Start.");
        // requestContext是用来获取请求和响应的。帮我们拿请求响应对象。
        RequestContext requestContext = RequestContext.getCurrentContext();
        //request就是拿到当前的请求
        HttpServletRequest request = requestContext.getRequest();
        //若是发往认证服务器的则直接返回，去做认证。
        if (StringUtils.startsWithIgnoreCase(request.getRequestURI(), "/token")) {
            return null;    //return null 就是走下一个filter
        }

        String authHeader = request.getHeader("Authorization");
        if (StringUtils.isBlank((authHeader))) {
            return null;
        }
        if (!StringUtils.startsWithIgnoreCase("authheader", "bearer ")) {
            return null;
        }
        try {
            TokenInfo info = getTokenInfo(authHeader);
            request.setAttribute("tokenInfo", info);
        } catch (Exception e) {
            logger.error("get token info fail", e);
        }
        return null;
    }

    private TokenInfo getTokenInfo(String authHeader) {
        String token = StringUtils.substringAfter(authHeader, "bearer ");
        String oauthServiceUrl = "http://localhost:8884/oauth/check_token";

        TokenInfo tokenInfo = new TokenInfo();
        return tokenInfo;
    }
}
