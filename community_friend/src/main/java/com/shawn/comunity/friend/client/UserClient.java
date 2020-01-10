package com.shawn.comunity.friend.client;

import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient("community-user")
public interface UserClient {

    @PutMapping("user/{userid}/{friendid}/{amount}")
    public Result addFanAndFollowCount(@PathVariable("userid") String userId, @PathVariable("friendid") String friendId, @PathVariable("amount") int amount);
}
