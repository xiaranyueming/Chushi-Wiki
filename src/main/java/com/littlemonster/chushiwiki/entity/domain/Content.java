package com.littlemonster.chushiwiki.entity.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 文档内容表
 * @author white-zhou
 * @TableName content
 */
@TableName(value ="content")
@Data
public class Content implements Serializable {
    /**
     * 内容id
     */
    @TableId
    @Schema(description = "内容id")
    private Integer id;

    /**
     * 内容
     */
    @Schema(description = "内容")
    private String content;

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