package com.shawn.community.search.controller;

import com.shawn.community.search.entity.Article;
import com.shawn.community.search.service.ArticleSearchService;
import entity.PageResult;
import entity.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/article")
public class ArticleSearchController {
    private final ArticleSearchService searchService;

    @PostMapping
    public Result save(@RequestBody Article article) {
        searchService.save(article);
        return Result.success();
    }



    @GetMapping("/search/{keywords}/{page}/{size}")
    public Result findByTitleLike(@PathVariable String keywords,
                                  @PathVariable int page, @PathVariable int size) {
        Page<Article> articlePage =
                searchService.findByTitleLike(keywords, page, size);
        return Result.success(PageResult.create(articlePage.getTotalElements(),
                articlePage.getContent()));
    }
}
