package com.littlemonster.chushiwiki.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.littlemonster.chushiwiki.entity.domain.Books;
import com.littlemonster.chushiwiki.entity.vo.BookVO;

import java.util.List;

/**
* @author white-zhou
* @description 针对表【books(书籍表)】的数据库操作Service
* @createDate 2024-04-03 20:57:30
*/
public interface BooksService extends IService<Books> {


    /**
     * 获取书籍列表
     * @return 书籍列表
     */
    List<BookVO> getBooksList();


    /**
     * 保存书籍
     * @param bookVO 书籍信息
     * @return 保存结果
     */
    boolean saveBook(BookVO bookVO);


    /**
     * 删除书籍
     * @param bookId 书籍ID
     * @return 删除结果
     */
    boolean deleteBook(Long bookId);


    /**
     * 更新书籍
     * @param keyWord 关键字
     * @return 搜索结果
     */
    List<BookVO> searchBook(String keyWord);
}
