package com.shawn.community.article.controller;


import com.shawn.community.article.entity.Article;
import com.shawn.community.article.service.ArticleService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService service;


    @PutMapping("thumbsup/{articleId}")
    public Result updateThumbsUp(@PathVariable String articleId) {
//        give a thumbs up ++;
        service.updateThumbsUp(articleId);
        return Result.success();
    }

    @PutMapping("/examine/{articleId}")
    public Result examine(@PathVariable String articleId) {
        int i = service.updateReviewState(articleId);
        String message = i == 0 ? "Review Failed" : "Review Successfully";
        return Result.error(StatusCode.Success, message);
    }


    @GetMapping("/{id}")
    public Result<Article> findById(@PathVariable String id) {
        return Result.success(service.findById(id));
    }

    @PostMapping("/search/{page}/{size}")
    public Result<PageResult<Article>> findSearch(@PathVariable("page") int page, @PathVariable("size") int size, @RequestBody Article article) {
        Page<Article> questionPage = service.findSearch(page, size, article);
        PageResult<Article> pageResult = PageResult.create(questionPage.getTotalElements(), questionPage.getContent());
        return Result.success(pageResult);
    }

    @GetMapping
    public Result<List<Article>> findAll() {
        return Result.success(service.findAll());
    }

    @PostMapping("/search")
    public Result<List<Article>> findSearch(@RequestBody Article article) {
        return Result.success(service.findSearch(article));
    }

    @PostMapping
    public Result save(@RequestBody Article article) {
        service.save(article);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result update(@RequestBody Article article, @PathVariable String id) {
        article.setId(id);
        service.update(article);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        service.deleteById(id);
        return Result.success();
    }
}
