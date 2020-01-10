package com.shawn.community.qa.controller;

import com.shawn.community.qa.entity.Reply;
import com.shawn.community.qa.service.ReplyService;
import entity.PageResult;
import entity.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(rollbackOn = Exception.class)
public class ReplyController {
    private final ReplyService service;

    @GetMapping("/{id}")
    public Result<Reply> findById(@PathVariable String id) {
        return Result.success(service.findById(id));
    }

    @PostMapping("/search/{page}/{size}")
    public Result<PageResult<Reply>> findSearch(@PathVariable("page") int page, @PathVariable("size") int size, @RequestBody Reply reply) {
        Page<Reply> replyPage = service.findSearch(page, size, reply);
        PageResult<Reply> pageResult = PageResult.create(replyPage.getTotalElements(), replyPage.getContent());
        return Result.success(pageResult);
    }

    @GetMapping
    public Result<List<Reply>> findAll() {
        return Result.success(service.findAll());
    }

    @PostMapping("/search")
    public Result<List<Reply>> findSearch(@RequestBody Reply reply) {
        return Result.success(service.findSearch(reply));
    }

    @PostMapping
    public Result save(@RequestBody Reply reply) {
        service.save(reply);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result update(@RequestBody Reply reply, @PathVariable String id) {
        reply.setId(id);
        service.update(reply);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        service.deleteById(id);
        return Result.success();
    }
}
