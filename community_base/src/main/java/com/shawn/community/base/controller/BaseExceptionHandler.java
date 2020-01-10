package com.shawn.community.base.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e) {
        return Result.error(StatusCode.ERROR, e.getLocalizedMessage());
    }
}
