package com.littlemonster.chushiwiki.common;

import lombok.Getter;

/**
 * @author: white-zhou
 * @date: 2024-04-03 21:07
 **/
@Getter
public enum ResponseCode {

    SYSTEM_ERROR(500, "系统错误"),
    NO_PARAM(400, "参数为空"),
    ERROR_PARAM(403, "参数错误");

    private int code;

    private String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
