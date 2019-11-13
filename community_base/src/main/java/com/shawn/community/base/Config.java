package com.shawn.community.base;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import util.IdWorker;

@Configuration
public class Config {
    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1,1);
    }
}
