package com.littlemonster.chushiwiki.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.littlemonster.chushiwiki.entity.domain.Content;
import com.littlemonster.chushiwiki.entity.vo.ContentVO;

/**
* @author white-zhou
* @description 针对表【content(文档内容表)】的数据库操作Service
* @createDate 2024-04-06 10:59:23
*/
public interface ContentService extends IService<Content> {


    /**
     * 获取文档内容
     * @param id 文档id
     * @return 文档内容
     */
    ContentVO getContentDetail(Integer id);
}
