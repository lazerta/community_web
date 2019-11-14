package com.shawn.recruit.controller;

import com.shawn.recruit.entity.Company;
import com.shawn.recruit.service.CompanyService;
import entity.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/company")
public class CompanyController {
    private CompanyService service;

    @GetMapping("/search/poplar")
    public Result<List<Company>> getPopularList() {
        List<Company> companyList = service.getPopularList();
        return Result.success(companyList);
    }

}
