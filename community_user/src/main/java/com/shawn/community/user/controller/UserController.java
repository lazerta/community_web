package com.shawn.community.user.controller;

import annotation.AdminRequired;
import com.shawn.community.user.Util.Const;
import com.shawn.community.user.entity.User;
import com.shawn.community.user.service.UserService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import util.JwtUtil;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final RedisTemplate redisTemplate;
    private final JwtUtil jwtUtil;


    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        User loginUser = userService.findByMobileAndPassword(user);
        if (loginUser == null) {
            return Result.error(StatusCode.ERROR, "login failed");
        }
        Map<String, Object> map = new HashMap<>();
        String token = jwtUtil.createJWT(user.getId(), user.getMobile(), Const.Role.USER);
        map.put("token", token);
        map.put("role", Const.Role.USER);
        return Result.success(map);
    }

    @GetMapping
    public Result findAll() {
        return Result.success(userService.findAll());
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {
        return Result.success(userService.findById(id));
    }


    @PutMapping("/{id}")
    public Result update(@RequestBody User user, @PathVariable String id) {
        user.setId(id);
        userService.update(user);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    @AdminRequired
    public Result deleteById(@PathVariable String id) {
        userService.deleteById(id);
        return Result.success();
    }

    @PostMapping("/sendsms/{mobile}")
    public Result sendVerificationMessage(@PathVariable String mobile) {
        userService.sendVerificationMessage(mobile);
        return Result.success();
    }

    @PostMapping("/register/{code}")
    public Result register(@PathVariable Integer code, @RequestBody User user) {
        Integer checkCode = (Integer) redisTemplate.opsForValue().get(Const.smsCode + user.getMobile());
        System.out.println(checkCode);
        if (checkCode == null) {
            return Result.error("acquire code first");
        }
        if (!checkCode.equals(code)) {
            return Result.error("incorrect code");
        }
        userService.register(user, code);
        return Result.success();
    }

    @GetMapping("/search/{page}/{size}")
    public Result findSearch(@RequestBody User user, @PathVariable int page, @PathVariable int size) {
        Page<User> search = userService.findSearch(user, page, size);
        PageResult<User> userPageResult = PageResult.create(search.getTotalElements(), search.getContent());
        return Result.success(userPageResult);
    }

    @GetMapping("/search")
    public Result findSearch(@RequestBody User user) {
        return Result.success(userService.findSearch(user));
    }

    @PutMapping("/{userid}/{friendid}/{amount}")
    public Result addFanAndFollowCount(@PathVariable("userid") String userId, @PathVariable String friendid, @PathVariable int amount) {
        userService.addFanAndFollowCount(userId, friendid,amount);
        return Result.success();
    }

}
