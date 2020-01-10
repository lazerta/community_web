package com.shawn.community.recruit.controller;

import com.shawn.community.recruit.entity.Company;
import com.shawn.community.recruit.service.CompanyService;
import entity.PageResult;
import entity.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService service;

    @GetMapping("/search/poplar")
    public Result<List<Company>> getPopularList() {
        List<Company> companyList = service.getPopularList();
        return Result.success(companyList);
    }

    @GetMapping("/{id}")
    public Result<Company> findById(@PathVariable String id) {
        return Result.success(service.findById(id));
    }

    @PostMapping("/search/{page}/{size}")
    public Result<PageResult<Company>> findSearch(@PathVariable("page") int page, @PathVariable("size") int size, @RequestBody Company company) {
        Page<Company> companyPage = service.findSearch(page, size, company);
        PageResult<Company> pageResult = PageResult.create(companyPage.getTotalElements(), companyPage.getContent());
        return Result.success(pageResult);
    }

    @GetMapping
    public Result<List<Company>> findAll() {
        return Result.success(service.findAll());
    }

    @PostMapping("/search")
    public Result<List<Company>> findSearch(@RequestBody Company company) {
        return Result.success(service.findSearch(company));
    }

    @PostMapping
    public Result save(@RequestBody Company company) {
        service.save(company);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result update(@RequestBody Company company, @PathVariable String id) {
        company.setId(id);
        service.update(company);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        service.deleteById(id);
        return Result.success();
    }
}
