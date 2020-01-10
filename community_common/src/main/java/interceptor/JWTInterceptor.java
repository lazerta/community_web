package interceptor;

import Const.Const;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;
import util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@AllArgsConstructor
@Slf4j
public class JWTInterceptor implements HandlerInterceptor {
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        final String header = request.getHeader(Const.HEADER);
        if (StringUtil.isNullOrBlank(header) || !header.startsWith(Const.SECRET_HEADER_TOKEN)) {
            return true;
        }
         log.trace("header = {}",header);
        final String token = header.substring(6);
        log.trace("token = {}",token);
        try {
            Claims claims = jwtUtil.parseJWT(token);
            if (claims == null) {
                return true;
            }

            if ("admin".equals(claims.get("role"))) {
                request.setAttribute(Const.ADMIN_TOKEN, Const.ADMIN_TOKEN);
            }
            if ("user".equals(claims.get("role"))) {
                request.setAttribute(Const.USER_TOKEN, Const.USER_TOKEN);
            }
            request.setAttribute("claim", claims);
        } catch (Exception e) {
            throw new RuntimeException("invalid token");
        }


        return true;
    }

}
