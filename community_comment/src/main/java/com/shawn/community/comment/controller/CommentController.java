package com.shawn.community.comment.controller;

import com.shawn.community.comment.entity.Comment;
import com.shawn.community.comment.service.CommentService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final RedisTemplate redisTemplate;


    @GetMapping()
    public Result findAll() {
        return Result.success(commentService.findAll());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable String id) {
        return Result.success(commentService.findById(id));
    }

    @PostMapping()
    public Result save(@RequestBody Comment comment) {
        commentService.save(comment);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable String id, @RequestBody Comment comment) {
        comment.set_id(id);
        commentService.update(comment);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id) {
        commentService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/comment/{parentId}/{page}/{size}")
    public Result<PageResult> findByParentId(@PathVariable String parentId, @PathVariable int page, @PathVariable int size) {
        Page<Comment> commentPage = commentService.findByParentId(parentId, page - 1, size);
        return Result.success(PageResult.create(commentPage.getTotalElements(), commentPage.getContent()));
    }

    @PutMapping("/thumbup/{id}")
    public Result updateThumbUp(@PathVariable String id) {
        String userId = "12";
//        check if user up vote before
        if (redisTemplate.opsForValue().get("thumb_" + userId) != null) {
            // up vote before
            return Result.error(StatusCode.ERROR, "no duplicated thumbs up");
        }
        commentService.updateThumbUp(id);
        redisTemplate.opsForValue().set("thumb_" + userId, 1);
        return Result.success();
    }
}
