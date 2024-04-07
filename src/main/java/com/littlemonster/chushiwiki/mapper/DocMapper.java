package com.littlemonster.chushiwiki.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.littlemonster.chushiwiki.entity.domain.Doc;

/**
* @author white-zhou
* @description 针对表【doc(文档表)】的数据库操作Mapper
* @createDate 2024-04-05 10:07:15
* @Entity com.littlemonster.chushiwiki.entity.domain.Doc
*/
public interface DocMapper extends BaseMapper<Doc> {

    /**
     * 点赞文档
     * @param docId 文档id
     * @return 点赞结果
     */
    boolean voteDoc(Integer docId);


    /**
     * 浏览文档
     * @param id 文档id
     * @return 浏览结果
     */
    boolean viewDoc(Integer id);
}




