package com.shawn.community.manager.filtter;

import Const.Const;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import util.JwtUtil;
import util.StringUtil;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class ManagerFilter extends ZuulFilter {
    private final JwtUtil jwtUtil;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        // when dispatcher for router there is not header
        // if it is login

        if (request.getMethod().equals("OPTIONS") || request.getRequestURL().indexOf("/admin/login")!= -1) {
            System.err.println("manager filter2");
            return null;
        }
        String header = request.getHeader(Const.HEADER);
        if (!StringUtil.isNullOrBlank(header) && header.startsWith(Const.SECRET_HEADER_TOKEN)) {
            String token = header.substring(6);
            try {
                Claims claims = jwtUtil.parseJWT(token);
                String role = (String) claims.get(Const.Role.ADMIN);
                if (!StringUtil.isNullOrBlank(role) && role.equals(Const.ADMIN_TOKEN)) {
                    currentContext.addZuulRequestHeader(Const.HEADER, header);
                    return null;
                }
            } catch (Exception e) {
                onInvalid(currentContext);
            }

        }
        onInvalid(currentContext);
        return null;
    }

    private void onInvalid(RequestContext currentContext) {
        currentContext.setSendZuulResponse(false);
        currentContext.setResponseStatusCode(403);
        currentContext.setResponseBody("unauthorized");
        currentContext.getResponse().setContentType("application/json");
    }
}
