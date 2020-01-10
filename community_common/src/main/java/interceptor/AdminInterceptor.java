package interceptor;

import Const.Const;
import annotation.AdminRequired;
import exception.UnauthorizedException;
import org.springframework.web.servlet.HandlerInterceptor;
import util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!Util.isHandlerAnnotatedWith(handler, AdminRequired.class)) {
            return true;
        }
        if (request.getAttribute(Const.ADMIN_TOKEN) == null) {
            throw UnauthorizedException.create("admin login required");
        }
        return true;
    }
}
