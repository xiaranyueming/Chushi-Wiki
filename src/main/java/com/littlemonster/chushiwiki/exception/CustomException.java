package com.littlemonster.chushiwiki.exception;

import com.littlemonster.chushiwiki.common.ResponseCode;
import lombok.Getter;

/**
 * @author: white-zhou
 * @date: 2024-04-03 21:11
 * @description: 自定义异常
 **/
@Getter
public class CustomException extends RuntimeException{

    private int code;

    private String message;



    public CustomException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }


    public CustomException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }

}
