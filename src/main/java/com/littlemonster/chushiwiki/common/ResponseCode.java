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
    LOGIN_ERROR(401, "用户名或密码错误"),
    NO_USER(402, "该用户不存在"),
    ERROR_PARAM(403, "参数错误"),
    HAS_USER(405, "该用户已存在"),
    PASSWORD_RULE_ERROR(407, "密码规则错误"),
    NO_PERMISSION(408, "无权限");

    private int code;

    private String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
