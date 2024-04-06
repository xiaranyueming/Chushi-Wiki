package com.littlemonster.chushiwiki.controller;

import com.littlemonster.chushiwiki.common.ResponseCode;
import com.littlemonster.chushiwiki.entity.vo.ContentVO;
import com.littlemonster.chushiwiki.service.ContentService;
import com.littlemonster.chushiwiki.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: white-zhou
 * @date: 2024-04-06 11:02
 **/
@RestController
@RequestMapping("/content")
@Tag(name = "文档内容管理")
public class ContentController {

    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }


    /**
     * 获取文档内容
     * @param id 文档id
     * @return 文档内容
     */
    @GetMapping("/detail/{id}")
    @Operation(summary = "获取文档内容")
    public Result getContentDetail(@PathVariable("id") Integer id) {
        if (id == null) {
            return Result.failure(ResponseCode.NO_PARAM);
        }

        ContentVO contentVO = contentService.getContentDetail(id);
        if (contentVO == null) {
            return Result.failure(ResponseCode.SYSTEM_ERROR);
        }

        return Result.success(contentVO);
    }
}
