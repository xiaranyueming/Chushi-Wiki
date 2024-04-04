package com.littlemonster.chushiwiki.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: white-zhou
 * @date: 2024-04-04 20:28
 **/
@Data
public class CategoryVO {

    /**
     * 分类id
     */
    @Schema(description = "分类id")
    private Integer id;

    /**
     * 父分类id
     */
    @Schema(description = "父分类id")
    private Integer parentId;

    /**
     * 分类名称
     */
    @Schema(description = "分类名称")
    private String categoryName;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sort;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
