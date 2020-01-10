package com.shawn.community.user.config;


import interceptor.JWTInterceptor;
import interceptor.UserLoginInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private JWTInterceptor jwtInterceptor;
    private UserLoginInterceptor userLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(jwtInterceptor)
                .order(Ordered.HIGHEST_PRECEDENCE)
                .addPathPatterns()
                .addPathPatterns("/**")
                .excludePathPatterns("/**/login");
        registry.addInterceptor(userLoginInterceptor);
    }

}
