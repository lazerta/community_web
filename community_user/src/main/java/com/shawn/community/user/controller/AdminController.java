package com.shawn.community.user.controller;


import com.shawn.community.user.Util.Const;
import com.shawn.community.user.entity.Admin;
import com.shawn.community.user.service.AdminService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final JwtUtil jwtUtil;


    @PostMapping("/login")
    public Result login(@RequestBody Admin admin) {
        Admin loginAdmin = adminService.login(admin);
        if (loginAdmin == null) {
            return Result.error(StatusCode.UNAUTHORIZED,"invalid combination of username and password");
        }
        String token = jwtUtil.createJWT(loginAdmin.getId(), loginAdmin.getUsername(), Const.Role.ADMIN);
        // return web token
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("role", Const.Role.ADMIN);
        return Result.success(data);
    }

    @GetMapping
    public Result findAll() {
        return Result.success(adminService.findAll());
    }

    @PostMapping
    public Result save(@RequestBody Admin admin) {
        adminService.save(admin);
        return Result.success();

    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {
        return Result.success(adminService.findById(id));
    }


    @PutMapping("/{id}")
    public Result update(@RequestBody Admin admin, @PathVariable String id) {
        admin.setId(id);
        adminService.update(admin);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id, HttpServletRequest request) {

        adminService.deleteById(id,request);
        return Result.success();
    }




    @GetMapping("/search/{page}/{size}")
    public Result findSearch(@RequestBody Admin admin, @PathVariable int page, @PathVariable int size) {
        Page<Admin> search = adminService.findSearch(admin, page, size);
        PageResult<Admin> userPageResult = PageResult.create(search.getTotalElements(), search.getContent());
        return Result.success(userPageResult);
    }

    @GetMapping("/search")
    public Result findSearch(@RequestBody Admin admin) {
        return Result.success(adminService.findSearch(admin));
    }

}
