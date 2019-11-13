package com.shawn.community.base.controller;

import com.shawn.community.base.entity.Label;
import com.shawn.community.base.service.LabelService;
import entity.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/label")
public class LabelController {
    private LabelService service;

    @GetMapping()
    public Result findAll() {
        return Result.success(service.findAll());
    }

    @GetMapping("/{labelId}")
    public Result findById(@PathVariable("labelId") String id) {
        return Result.success(service.findById(id));
    }

    @PostMapping
    public Result save(@RequestBody Label label) {
        service.save(label);
        return Result.success();
    }

    @RequestMapping(value = "/{labelId}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public Result update(@RequestBody Label label, @PathVariable("labelId") String id) {
        label.setId(id);
        service.update(label);
        return Result.success();
    }

    @DeleteMapping("/{labelId}")
    public Result delete(@RequestBody Label label, @PathVariable("labelId") String id) {
        service.deleteById(id);
        return Result.success();
    }

    @PostMapping("/search")
    public Result findSearch(@RequestBody Label label) {
        List<Label> list = service.findSearch(label);
      return   Result.success();
    }
}
