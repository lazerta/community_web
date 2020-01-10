package com.shawn.comunity.friend.config;

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
    private JWTInterceptor adminInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(adminInterceptor).order(Ordered.HIGHEST_PRECEDENCE)
                .addPathPatterns("/**")
                .excludePathPatterns("/**/login");
        registry.addInterceptor(new UserLoginInterceptor());
    }
}
