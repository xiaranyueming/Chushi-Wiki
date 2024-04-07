package com.littlemonster.chushiwiki.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.littlemonster.chushiwiki.entity.domain.Books;

/**
* @author white-zhou
* @description 针对表【books(书籍表)】的数据库操作Mapper
* @createDate 2024-04-03 20:57:30
* @Entity generator.domain.Books
*/
public interface BooksMapper extends BaseMapper<Books> {


    /**
     * 更新书籍的浏览量，点赞量，文档数量
     * @return 更新结果
     */
    boolean updateBookInfo();
}




