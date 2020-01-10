package interceptor;

import Const.Const;
import annotation.LoginRequired;
import exception.UnauthorizedException;
import org.springframework.web.servlet.HandlerInterceptor;
import util.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!Util.isHandlerAnnotatedWith(handler, LoginRequired.class)) {
            return true;
        }
        if (request.getAttribute(Const.CLAIM) == null) {
            throw UnauthorizedException.create("User login required");
        }
        return true;
    }
}
