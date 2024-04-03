package com.littlemonster.chushiwiki;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.littlemonster.chushiwiki.mapper")
public class ChushiWikiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChushiWikiApplication.class, args);
    }

}
