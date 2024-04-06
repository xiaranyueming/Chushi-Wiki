package com.littlemonster.chushiwiki.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: white-zhou
 * @date: 2024-04-05 10:09
 **/
@Data
public class DocVO {

    /**
     * 文档id
     */
    @Schema(description = "文档id")
    private Integer id;

    /**
     * 书籍id
     */
    @Schema(description = "书籍id")
    private Integer bookId;

    /**
     * 父id
     */
    @Schema(description = "父id")
    private Integer parentId;

    /**
     * 文档名称
     */
    @Schema(description = "文档名称")
    private String docName;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sort;

    /**
     * 内容
     */
    @Schema(description = "内容")
    private String content;

    /**
     * 浏览量
     */
    @Schema(description = "浏览量")
    private Integer viewCount;

    /**
     * 点赞数
     */
    @Schema(description = "点赞数")
    private Integer voteCount;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
