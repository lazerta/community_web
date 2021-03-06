package com.shawn.comunity.friend.controller;

import entity.Result;
import entity.StatusCode;
import exception.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BaseExceptionController {

    @ExceptionHandler(UnauthorizedException.class)
    public Result unAuthorizedException(UnauthorizedException e) {

        if (e.getMessage() == null) {
            return Result.error(StatusCode.UNAUTHORIZED);
        }
        return Result.error(StatusCode.UNAUTHORIZED, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.error(StatusCode.ERROR, e.getMessage());
    }
}
