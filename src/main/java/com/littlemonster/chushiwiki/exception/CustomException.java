package com.littlemonster.chushiwiki.exception;

import com.littlemonster.chushiwiki.common.ResponseCode;

/**
 * @author: white-zhou
 * @date: 2024-04-03 21:11
 * @description: 自定义异常
 **/
public class CustomException extends RuntimeException{

    public CustomException(String message) {
        super(message);
    }


    public CustomException(ResponseCode responseCode) {
        super(responseCode.getMessage());
    }

}
