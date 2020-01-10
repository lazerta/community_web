package com.shawn.community.meetup.controller;

import com.shawn.community.meetup.entity.Meetup;
import com.shawn.community.meetup.service.MeetUpService;
import entity.PageResult;
import entity.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meetup")
@RequiredArgsConstructor
@CrossOrigin
public class MeetupController {
    private final MeetUpService service;

    @GetMapping("/{id}")
    public Result<Meetup> findById(@PathVariable String id) {
        return Result.success(service.findById(id));
    }

    @PostMapping("/search/{page}/{size}")
    public Result<PageResult<Meetup>> findSearch(@PathVariable("page") int page, @PathVariable("size") int size, @RequestBody Meetup meetUp) {
        Page<Meetup> questionPage = service.findSearch(page, size, meetUp);
        PageResult<Meetup> pageResult = PageResult.create(questionPage.getTotalElements(), questionPage.getContent());
        return Result.success(pageResult);
    }

    @GetMapping
    public Result<List<Meetup>> findAll() {
        return Result.success(service.findAll());
    }

    @PostMapping("/search")
    public Result<List<Meetup>> findSearch(@RequestBody Meetup meetUp) {
        return Result.success(service.findSearch(meetUp));
    }

    @PostMapping
    public Result save(@RequestBody Meetup meetUp) {
        service.save(meetUp);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result update(@RequestBody Meetup meetUp, @PathVariable String id) {
        meetUp.setId(id);
        service.update(meetUp);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        service.deleteById(id);
        return Result.success();
    }
}
