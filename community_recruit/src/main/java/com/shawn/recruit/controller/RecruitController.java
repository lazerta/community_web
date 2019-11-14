package com.shawn.recruit.controller;

import com.shawn.recruit.entity.Recruit;
import com.shawn.recruit.service.RecruitService;
import entity.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recruit")
@AllArgsConstructor
public class RecruitController {
    private RecruitService service;

    @GetMapping("/search/recommend")
    public Result<List<Recruit>> getRecommendedRecruit() {
        List<Recruit> recommends = service.recommendedRecruitList();
        return Result.success(recommends);
    }
    @GetMapping("search/latest")
    public Result<List<Recruit>> getLatest() {
        return Result.success(service.latestRecruitList());
    }
}
