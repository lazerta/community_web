package com.shawn.community.qa;

import interceptor.JWTInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import util.IdWorker;
import util.JwtUtil;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
public class QaApplication {
    public static void main(String[] args) {
        SpringApplication.run(QaApplication.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }

   @Bean
   public JwtUtil jwtUtil() {
       return new JwtUtil();
   }
    @Bean
    @DependsOn({"jwtUtil"})
    public JWTInterceptor jwtInterceptor(){
      return   new JWTInterceptor(jwtUtil());
    }
}
