package com.littlemonster.chushiwiki.entity.domain;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 书籍表
 * @TableName books
 */
@TableName(value ="books")
@Data
public class Books implements Serializable {
    /**
     * 书籍id
     */
    @TableId(type = IdType.AUTO)
    @Schema(description = "书籍id")
    private Integer id;

    /**
     * 书籍名称
     */
    @Schema(description = "书籍名称")
    private String bookName;

    /**
     * 一级分类id
     */
    @Schema(description = "一级分类id")
    private Integer category1Id;

    /**
     * 二级分类id
     */
    @Schema(description = "二级分类id")
    private Integer category2Id;

    /**
     * 封面
     */
    @Schema(description = "封面")
    private String cover;

    /**
     * 描述
     */
    @Schema(description = "描述")
    private String description;

    /**
     * 文档数量
     */
    @Schema(description = "文档数量")
    private Integer docCount;

    /**
     * 浏览数量
     */
    @Schema(description = "浏览数量")
    private Integer viewCount;

    /**
     * 点赞数量
     */
    @Schema(description = "点赞数量")
    private Integer voteCount;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    /**
     * 是否删除 0-未删除 1-已删除
     */
    @Schema(description = "是否删除 0-未删除 1-已删除")
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}