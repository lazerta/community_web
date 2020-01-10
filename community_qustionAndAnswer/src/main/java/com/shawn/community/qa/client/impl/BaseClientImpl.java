package com.shawn.community.qa.client.impl;

import com.shawn.community.qa.client.BaseClient;
import entity.Result;
import org.springframework.stereotype.Component;

@Component
public class BaseClientImpl implements BaseClient {
    @Override
    public Result findById(String id) {
        return Result.error("not service available");
    }
}
