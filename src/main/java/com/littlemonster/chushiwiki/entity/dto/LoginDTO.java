package com.littlemonster.chushiwiki.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author: white-zhou
 * @date: 2024-04-06 18:50
 **/
@Data
public class LoginDTO {
    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String userName;

    /**
     * 密码
     */
    @Schema(description = "密码")
    private String password;
}
