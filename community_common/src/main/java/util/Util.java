package util;

import Const.Const;
import io.jsonwebtoken.Claims;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Util {
    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static boolean isUserLogin(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute(Const.USER_TOKEN);
        return claims != null;
    }

    public static boolean isHandlerAnnotatedWith(Object handler, Class annotation) {
        if (!(handler instanceof HandlerMethod)) {
            return false;
        }
        return ((HandlerMethod) handler).hasMethodAnnotation(annotation);

    }
}
