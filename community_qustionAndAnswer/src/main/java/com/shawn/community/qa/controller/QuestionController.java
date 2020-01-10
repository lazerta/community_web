package com.shawn.community.qa.controller;

import Const.Const;
import com.shawn.community.qa.client.BaseClient;
import com.shawn.community.qa.entity.Question;
import com.shawn.community.qa.service.QuestionService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/question")
@AllArgsConstructor
public class QuestionController {
    private QuestionService service;
    private BaseClient baseClient;

    @GetMapping("/latest/{questionId}/{page}/{size}")
    public Result<PageResult<Question>> findLatestProblemsOfLabel(@PathVariable("questionId") String questionId, @PathVariable("page") int page, @PathVariable("size") int size) {
        Page<Question> questionPage = service.findLatestProblemsOfLabel(questionId, page, size);
        PageResult<Question> questionPageResult = PageResult.create(questionPage.getTotalElements(), questionPage.getContent());
        return Result.success(questionPageResult);

    }


    @GetMapping("/popular/{questionId}/{page}/{size}")
    public Result<PageResult<Question>> findMostPopularQuestionsOfLabel(@PathVariable("questionId") String questionId, @PathVariable("page") int page, @PathVariable("size") int size) {
        Page<Question> questionPage = service.findMostPopularQuestionsOfLabel(questionId, page, size);
        PageResult<Question> questionPageResult = PageResult.create(questionPage.getTotalElements(), questionPage.getContent());
        return Result.success(questionPageResult);

    }


    @GetMapping("/noreply/{questionId}/{page}/{size}")
    public Result<PageResult<Question>> findNotAnsweredQuestionOfLabel(@PathVariable("questionId") String questionId, @PathVariable("page") int page, @PathVariable("size") int size) {
        Page<Question> questionPage = service.findNotReplyQuestionOfLabel(questionId, page, size);
        PageResult<Question> questionPageResult = PageResult.create(questionPage.getTotalElements(), questionPage.getContent());
        return Result.success(questionPageResult);

    }

    @GetMapping("/label/{labelId}")
    public Result findByLabelId(@PathVariable String labelId) {
       return baseClient.findById(labelId);

    }

    @GetMapping("/{id}")
    public Result<Question> findById(@PathVariable String id) {
        return Result.success(service.findById(id));
    }


    @PostMapping("/search/{page}/{size}")
    public Result<PageResult<Question>> findSearch(@PathVariable("page") int page, @PathVariable("size") int size, @RequestBody Question question) {
        Page<Question> questionPage = service.findSearch(page, size, question);
        PageResult<Question> pageResult = PageResult.create(questionPage.getTotalElements(), questionPage.getContent());
        return Result.success(pageResult);
    }

    @GetMapping
    public Result<List<Question>> findAll() {
        return Result.success(service.findAll());
    }

    @PostMapping("/search")
    public Result<List<Question>> findSearch(@RequestBody Question question) {
        return Result.success(service.findSearch(question));
    }

    @PostMapping
    public Result save(@RequestBody Question question, HttpServletRequest request) {
        String token = (String) request.getAttribute(Const.USER_TOKEN);
        if (StringUtil.isNullOrBlank(token)) {
            return Result.error(StatusCode.UNAUTHORIZED);

        }
        service.save(question);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result update(@RequestBody Question question, @PathVariable String id) {
        question.setId(id);
        service.update(question);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        service.deleteById(id);
        return Result.success();
    }
}
