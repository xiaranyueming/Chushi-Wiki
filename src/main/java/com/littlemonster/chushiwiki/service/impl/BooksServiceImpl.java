package com.littlemonster.chushiwiki.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.littlemonster.chushiwiki.common.ResponseCode;
import com.littlemonster.chushiwiki.entity.domain.Books;
import com.littlemonster.chushiwiki.entity.vo.BookVO;
import com.littlemonster.chushiwiki.exception.CustomException;
import com.littlemonster.chushiwiki.mapper.BooksMapper;
import com.littlemonster.chushiwiki.service.BooksService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
* @author white-zhou
* @description 针对表【books(书籍表)】的数据库操作Service实现
* @createDate 2024-04-03 20:57:30
*/
@Service
public class BooksServiceImpl extends ServiceImpl<BooksMapper, Books>
    implements BooksService{

    private final BooksMapper booksMapper;

    public BooksServiceImpl(BooksMapper booksMapper) {
        this.booksMapper = booksMapper;
    }



    /**
     * 获取书籍列表
     * @return 书籍列表
     */
    @Override
    public List<BookVO> getBooksList() {

        List<Books> booksList = booksMapper.selectList(null);

        // 如果查询结果为空，则返回一个空的List
        if (CollectionUtil.isEmpty(booksList)) {
            return new ArrayList<BookVO>();
        }

        List<BookVO> bookVOList = new ArrayList<>();
        for (Books book : booksList) {
            BookVO bookVO = BeanUtil.copyProperties(book, BookVO.class);

            bookVOList.add(bookVO);
        }

        return bookVOList;
    }


    /**
     * 保存书籍
     * @param bookVO 书籍信息
     * @return 保存结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveBook(BookVO bookVO) {
        if (bookVO == null) {
            throw new CustomException(ResponseCode.NO_PARAM);
        }

        Books books = BeanUtil.copyProperties(bookVO, Books.class);
        if (books.getId() == null) {
            // id为空，执行插入操作
            return booksMapper.insert(books) > 0;
        } else {
            // id不为空，先查询是否存在该记录
            Books selectById = booksMapper.selectById(books.getId());
            Optional.ofNullable(selectById)
                    .orElseThrow(() -> new CustomException(500, "该书籍不存在"));
            // 执行更新操作
            return booksMapper.updateById(books) > 0;
        }
    }




    /**
     * 删除书籍
     * @param bookId 书籍ID
     * @return 删除结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBook(Long bookId) {
        if (bookId == null) {
            throw new CustomException(ResponseCode.NO_PARAM);
        }

        int deleteById = booksMapper.deleteById(bookId);
        if (deleteById <= 0) {
            throw new CustomException(500, "删除失败");
        }

        return true;
    }



    /**
     * 搜索书籍
     * @param keyWord 关键字
     * @return 搜索结果
     */
    @Override
    public List<BookVO> searchBook(String keyWord) {
        LambdaQueryWrapper<Books> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Books::getBookName, keyWord);
        List<Books> booksList = booksMapper.selectList(queryWrapper);

        // 如果查询结果为空，则返回一个空的List
        if (CollectionUtil.isEmpty(booksList)) {
            return new ArrayList<>();
        }

        List<BookVO> bookVOList = new ArrayList<>();
        for (Books book : booksList) {
            BookVO bookVO = BeanUtil.copyProperties(book, BookVO.class);

            bookVOList.add(bookVO);
        }

        return bookVOList;
    }
}




