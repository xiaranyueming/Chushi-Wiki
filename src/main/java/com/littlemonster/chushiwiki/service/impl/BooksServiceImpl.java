package com.littlemonster.chushiwiki.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.littlemonster.chushiwiki.entity.domain.Books;
import com.littlemonster.chushiwiki.mapper.BooksMapper;
import com.littlemonster.chushiwiki.service.BooksService;
import org.springframework.stereotype.Service;

/**
* @author white-zhou
* @description 针对表【books(书籍表)】的数据库操作Service实现
* @createDate 2024-04-03 20:57:30
*/
@Service
public class BooksServiceImpl extends ServiceImpl<BooksMapper, Books>
    implements BooksService{

}




