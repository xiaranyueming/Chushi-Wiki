package com.littlemonster.chushiwiki.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.littlemonster.chushiwiki.common.ResponseCode;
import com.littlemonster.chushiwiki.entity.vo.DocVO;
import com.littlemonster.chushiwiki.service.DocService;
import com.littlemonster.chushiwiki.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: white-zhou
 * @date: 2024-04-04 20:26:08
 **/
@RestController
@RequestMapping("/doc")
@Tag(name = "文档管理")
public class DocController {

    private final DocService docService;

    public DocController(DocService docService) {
        this.docService = docService;
    }



    /**
     * 获取文档列表
     * @return 文档列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取文档列表")
    public Result getDocList() {

        List<DocVO> docList = docService.getDocList();

        return Result.success(docList);
    }



    /**
     * 保存文档
     * @param docVO 文档信息
     * @return 保存结果
     */
    @PostMapping("/save")
    @Operation(summary = "保存文档")
    public Result saveDoc(@RequestBody DocVO docVO) {
        if (docVO == null) {
            return Result.failure(ResponseCode.NO_PARAM);
        }

        boolean saved = docService.saveDoc(docVO);
        if (!saved) {
            return Result.failure(500, "保存失败");
        }
        return Result.success();
    }



    /**
     * 删除文档
     * @param docId 文档ID
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除文档")
    public Result deleteDoc(@PathVariable("id") Long docId) {
        if (docId == null) {
            return Result.failure(ResponseCode.NO_PARAM);
        }

        boolean deleted = docService.deleteDoc(docId);
        if (!deleted) {
            return Result.failure(500, "删除失败");
        }

        return Result.success();
    }



    /**
     * 搜索文档
     * @param keyWord 关键字
     * @return 搜索结果
     */
    @GetMapping("/search")
    @Operation(summary = "搜索文档")
    public Result searchDoc(@RequestParam("keyWord") String keyWord) {
        List<DocVO> docList = docService.searchDoc(keyWord);
        if (CollectionUtil.isEmpty(docList)) {
            return Result.failure(500, "未找到相关文档");
        }

        return Result.success(docList);
    }
}
