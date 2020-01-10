package com.shawn.community.front.filtter;

import Const.Const;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.core.Ordered;
import util.StringUtil;

import javax.servlet.http.HttpServletRequest;

public class JwtHeaderFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String header = request.getHeader(Const.HEADER);
        if (!StringUtil.isNullOrBlank(header)){
            currentContext.addZuulRequestHeader(Const.HEADER, header);
        }

        return null;
    }
}
