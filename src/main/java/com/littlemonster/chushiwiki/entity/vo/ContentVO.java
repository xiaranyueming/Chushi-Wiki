package com.littlemonster.chushiwiki.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author: white-zhou
 * @date: 2024-04-06 15:17
 **/
@Data
public class ContentVO {
    /**
     * 内容id
     */
    @Schema(description = "内容id")
    private Integer id;

    /**
     * 内容
     */
    @Schema(description = "内容")
    private String content;
}
