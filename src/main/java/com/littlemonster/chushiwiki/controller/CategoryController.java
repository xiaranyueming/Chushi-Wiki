package com.littlemonster.chushiwiki.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.littlemonster.chushiwiki.common.ResponseCode;
import com.littlemonster.chushiwiki.entity.vo.CategoryVO;
import com.littlemonster.chushiwiki.service.CategoryService;
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
@RequestMapping("/category")
@Tag(name = "分类管理")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }



    /**
     * 获取分类列表
     * @return 分类列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取分类列表")
    public Result getCategoryList() {

        List<CategoryVO> categoryList = categoryService.getCategoryList();

        return Result.success(categoryList);
    }



    /**
     * 保存分类
     * @param categoryVO 分类信息
     * @return 保存结果
     */
    @PostMapping("/save")
    @Operation(summary = "保存分类")
    public Result saveCategory(@RequestBody CategoryVO categoryVO) {
        if (categoryVO == null) {
            return Result.failure(ResponseCode.NO_PARAM);
        }

        boolean saved = categoryService.saveCategory(categoryVO);
        if (!saved) {
            return Result.failure(500, "保存失败");
        }
        return Result.success();
    }



    /**
     * 删除分类
     * @param categoryId 分类ID
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除分类")
    public Result deleteCategory(@PathVariable("id") Long categoryId) {
        if (categoryId == null) {
            return Result.failure(ResponseCode.NO_PARAM);
        }

        boolean deleted = categoryService.deleteCategory(categoryId);
        if (!deleted) {
            return Result.failure(500, "删除失败");
        }

        return Result.success();
    }



    /**
     * 搜索分类
     * @param keyWord 关键字
     * @return 搜索结果
     */
    @GetMapping("/search")
    @Operation(summary = "搜索分类")
    public Result searchCategory(@RequestParam("keyWord") String keyWord) {
        List<CategoryVO> categoryList = categoryService.searchCategory(keyWord);
        if (CollectionUtil.isEmpty(categoryList)) {
            return Result.failure(500, "未找到相关分类");
        }

        return Result.success(categoryList);
    }
}
