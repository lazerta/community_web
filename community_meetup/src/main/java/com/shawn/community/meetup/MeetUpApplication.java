package com.shawn.meetup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

@SpringBootApplication
@EnableEurekaClient
public class MeetUpApplication {
    public static void main(String[] args) {
        SpringApplication.run(MeetUpApplication.class, args);

    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1, 1);
    }
}
