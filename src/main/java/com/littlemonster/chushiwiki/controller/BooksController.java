package com.littlemonster.chushiwiki.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.littlemonster.chushiwiki.common.ResponseCode;
import com.littlemonster.chushiwiki.entity.vo.BookVO;
import com.littlemonster.chushiwiki.service.BooksService;
import com.littlemonster.chushiwiki.utils.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: white-zhou
 * @date: 2024-04-03 21:21
 **/
@RestController
@RequestMapping("/books")
@Tag(name = "书籍管理")
public class BooksController {

    private final BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }



    /**
     * 获取书籍列表
     * @return 书籍列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取书籍列表")
    public Result getBooksList() {

        List<BookVO> booksList = booksService.getBooksList();

        return Result.success(booksList);
    }



    /**
     * 保存书籍
     * @param bookVO 书籍信息
     * @return 保存结果
     */
    @PostMapping("/save")
    @Operation(summary = "保存书籍")
    public Result saveBook(@RequestBody BookVO bookVO) {
        if (bookVO == null) {
            return Result.failure(ResponseCode.NO_PARAM);
        }

        boolean saved = booksService.saveBook(bookVO);
        if (!saved) {
            return Result.failure(500, "保存失败");
        }
        return Result.success();
    }



    /**
     * 删除书籍
     * @param bookId 书籍ID
     * @return 删除结果
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除书籍")
    public Result deleteBook(@PathVariable("id") Long bookId) {
        if (bookId == null) {
            return Result.failure(ResponseCode.NO_PARAM);
        }

        boolean deleted = booksService.deleteBook(bookId);
        if (!deleted) {
            return Result.failure(500, "删除失败");
        }

        return Result.success();
    }



    /**
     * 搜索书籍
     * @param keyWord 关键字
     * @return 搜索结果
     */
    @GetMapping("/search")
    @Operation(summary = "搜索书籍")
    public Result searchBook(@RequestParam("keyWord") String keyWord) {
        List<BookVO> bookList = booksService.searchBook(keyWord);
        if (CollectionUtil.isEmpty(bookList)) {
            return Result.failure(500, "未找到相关书籍");
        }

        return Result.success(bookList);
    }
}
