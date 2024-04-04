package com.littlemonster.chushiwiki.controller;

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
}
