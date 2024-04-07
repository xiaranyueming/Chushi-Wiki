package com.littlemonster.chushiwiki.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.littlemonster.chushiwiki.entity.domain.Doc;
import com.littlemonster.chushiwiki.entity.vo.DocVO;

import java.util.List;

/**
* @author white-zhou
* @description 针对表【doc(文档表)】的数据库操作Service
* @createDate 2024-04-05 10:07:15
*/
public interface DocService extends IService<Doc> {


    /**
     * 获取文档列表
     * @return 文档列表
     */
    List<DocVO> getDocList();


    /**
     * 保存文档
     * @param docVO 文档信息
     * @return 保存结果
     */
    boolean saveDoc(DocVO docVO);


    /**
     * 删除文档
     * @param idList 文档id
     * @return 删除结果
     */
    boolean deleteDoc(List<String> idList);


    /**
     * 搜索文档
     * @param keyWord 关键字
     * @return 文档列表
     */
    List<DocVO> searchDoc(String keyWord);


    /**
     * 获取文档详情
     * @param bookId 书籍id
     * @return 文档列表
     */
    List<DocVO> getDocByBookId(Integer bookId);



    /**
     * 点赞文档
     * @param docId 文档id
     * @return 点赞结果
     */
    boolean voteDoc(Integer docId);
}
