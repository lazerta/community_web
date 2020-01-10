package com.shawn.community.qa.client;

import com.shawn.community.qa.client.impl.BaseClientImpl;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "community-base" ,fallback = BaseClientImpl.class)
public interface BaseClient {
    @GetMapping("/label/{labelId}")
    public Result findById(@PathVariable("labelId") String id);
}
