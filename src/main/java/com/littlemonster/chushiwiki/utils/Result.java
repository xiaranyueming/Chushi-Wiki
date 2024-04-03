package com.littlemonster.chushiwiki.utils;

import com.littlemonster.chushiwiki.common.ResponseCode;
import lombok.Data;

/**
 * @author: white-zhou
 * @date: 2024-04-03 21:04
 **/
@Data
public class Result {

    private int code;

    private String message;

    private Object data;


    // 成功返回
    public static Result success(Object data){
        Result result = new Result();
        result.setCode(200);
        result.setMessage("success");
        result.setData(data);
        return result;
    }


    // 成功返回--无数据
    public static Result success(){
        return success(null);
    }


    // 失败返回--自定义错误信息
    public static Result failure(String message){
        Result result = new Result();
        result.setCode(500);
        result.setMessage(message);
        return result;
    }


    // 失败返回--枚举错误信息
    public static Result failure(ResponseCode responseCode){
        Result result = new Result();
        result.setCode(responseCode.getCode());
        result.setMessage(responseCode.getMessage());
        return result;
    }
}
