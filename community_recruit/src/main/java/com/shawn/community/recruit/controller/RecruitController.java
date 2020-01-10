package com.shawn.community.recruit.controller;

import com.shawn.community.recruit.entity.Recruit;
import com.shawn.community.recruit.service.RecruitService;
import entity.PageResult;
import entity.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recruit")
@RequiredArgsConstructor
public class RecruitController {
    private final RecruitService service;

    @GetMapping("/search/recommend")
    public Result<List<Recruit>> getRecommendedRecruit() {
        List<Recruit> recommends = service.recommendedRecruitList();
        return Result.success(recommends);
    }

    @GetMapping("search/latest")
    public Result<List<Recruit>> getLatest() {
        return Result.success(service.latestRecruitList());
    }

    @GetMapping
    public Result<List<Recruit>> findAll() {
        return Result.success(service.findAll());
    }

    @GetMapping("/{id}")
    public Result<Recruit> findById(@PathVariable String id) {
        return Result.success(service.findById(id));
    }

    @PostMapping("/search/{page}/{size}")
    public Result<PageResult<Recruit>> findSearch(@RequestBody Recruit recruit, @PathVariable int page, @PathVariable int size) {
        Page<Recruit> recruitPage = service.findSearch(recruit, page, size);
        return Result.success(PageResult.create(recruitPage.getTotalElements(), recruitPage.getContent()));
    }

    @PostMapping("/search")
    public Result<List<Recruit>> findSearch(@RequestBody Recruit recruit) {
        return Result.success(service.findSearch(recruit));
    }

    @PostMapping("/save")
    public  Result save(@RequestBody Recruit recruit){
        service.save(recruit);
        return Result.success();
    }
    @PutMapping("/{id}")
    public  Result update(@RequestBody Recruit recruit, @PathVariable String id){
        recruit.setId(id);
        service.update(recruit);
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id){
        service.deleteById(id);
        return Result.success();
    }


}
