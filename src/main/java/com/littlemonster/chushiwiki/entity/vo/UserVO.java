package com.littlemonster.chushiwiki.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @author: white-zhou
 * @date: 2024-04-06 18:51
 **/
@Data
public class UserVO {
    /**
     * 用户id
     */
    @Schema(description = "用户id")
    private Integer id;

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    private String userName;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private Date createTime;

    /**
     * token
     */
    @Schema(description = "token")
    private String token;
}
