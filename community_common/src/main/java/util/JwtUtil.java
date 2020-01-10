package util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Data
@ConfigurationProperties("jwt.config")
@Component
public class JwtUtil {

    private  String secretKey;

    private  long duration;

    private  ChronoUnit durationUnit = ChronoUnit.MINUTES;


    public String createJWT(String id, String subject, String roles) {
        LocalDateTime dateTime = LocalDateTime.now().plus(duration, durationUnit);
        JwtBuilder builder = Jwts.builder().setId(id)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, secretKey).claim("roles", roles);
        if (duration > 0) {
            builder.setExpiration(Util.toDate(dateTime));
        }
        return builder.compact();
    }

    public Claims parseJWT(String jwtStr) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwtStr)
                .getBody();
    }

}
