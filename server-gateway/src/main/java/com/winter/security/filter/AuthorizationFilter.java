package com.winter.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.winter.security.model.TokenInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName : AuthorizationFilter
 * @Description : 授权的过滤器
 * @Author : Winter
 * @Date: 2020-11-05 10:16
 */
@Component
public class AuthorizationFilter extends ZuulFilter {

    private Logger logger = Logger.getLogger(this.getClass());

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 3;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        // requestContext是用来获取请求和响应的。帮我们拿请求响应对象。
        RequestContext requestContext = RequestContext.getCurrentContext();
        //request就是拿到当前的请求
        HttpServletRequest request = requestContext.getRequest();
        if (isNeedAuth(request)) {
            // 取出OAuthFilter中加入的token信息
            TokenInfo tokenInfo = (TokenInfo) request.getAttribute("tokenInfo");
            logger.info("tokeninfo -> " + JSONObject.toJSONString(tokenInfo));
            if (tokenInfo != null && tokenInfo.isActive()) {
                if (!hasPermission(tokenInfo, request)) {
                    logger.info("audit log update fail 403");
                    handleError(403, requestContext);
                }
                requestContext.addZuulRequestHeader("username", tokenInfo.getUser_name());
            } else {
                if (!StringUtils.startsWith(request.getRequestURI(), "/token")) {


                    logger.info("Audit log update fail 401");
                    handleError(401, requestContext);
                }
            }
        }
        return null;
    }

    private boolean hasPermission(TokenInfo tokenInfo, HttpServletRequest request) {
        return RandomUtils.nextInt() % 2 == 0;
    }

    private void handleError(int status, RequestContext requestContext) {
        requestContext.getResponse().setContentType("application/json");
        requestContext.setResponseStatusCode(status);   //设置状态吗
        requestContext.setResponseBody("{\"message\":\"auth fail\"}"); //最终返回的json
        requestContext.setSendZuulResponse(false);  //表示不往下走了

    }

    private boolean isNeedAuth(HttpServletRequest request) {
        return true;
    }
}
