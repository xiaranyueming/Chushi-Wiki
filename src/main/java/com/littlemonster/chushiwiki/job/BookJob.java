package com.littlemonster.chushiwiki.job;

import com.littlemonster.chushiwiki.service.BooksService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author: white-zhou
 * @date: 2024-04-07 17:10
 * @description: 书籍定时任务
 **/
@Component
public class BookJob {

    private final BooksService booksService;

    public BookJob(BooksService booksService) {
        this.booksService = booksService;
    }


    /**
     * 更新书籍的浏览量，点赞量，文档数量
     * 每一小时执行一次 0 0 0/1 * * ?
     */
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void updateBookInfo() {
        booksService.updateBookInfo();
    }
}
