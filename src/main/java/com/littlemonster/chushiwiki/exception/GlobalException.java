package com.littlemonster.chushiwiki.exception;

import com.littlemonster.chushiwiki.utils.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: white-zhou
 * @date: 2024-04-03 21:02
 * @description: 全局异常处理
 **/
@ControllerAdvice
@ResponseBody
public class GlobalException {

    @ExceptionHandler(value = CustomException.class)
    private Result globalExceptionHandler(CustomException e) {
        return Result.failure(e.getCode(), e.getMessage());
    }

}
