package com.littlemonster.chushiwiki.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.littlemonster.chushiwiki.common.ResponseCode;
import com.littlemonster.chushiwiki.entity.domain.Content;
import com.littlemonster.chushiwiki.entity.domain.Doc;
import com.littlemonster.chushiwiki.entity.vo.DocVO;
import com.littlemonster.chushiwiki.exception.CustomException;
import com.littlemonster.chushiwiki.mapper.ContentMapper;
import com.littlemonster.chushiwiki.mapper.DocMapper;
import com.littlemonster.chushiwiki.service.DocService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
* @author white-zhou
* @description 针对表【doc(文档表)】的数据库操作Service实现
* @createDate 2024-04-05 10:07:15
*/
@Service
@Slf4j
public class DocServiceImpl extends ServiceImpl<DocMapper, Doc>
    implements DocService{

    private final DocMapper docMapper;
    private final ContentMapper contentMapper;

    public DocServiceImpl(DocMapper docMapper, ContentMapper contentMapper) {
        this.docMapper = docMapper;
        this.contentMapper = contentMapper;
    }



    /**
     * 获取文档列表
     * @return 文档列表
     */
    @Override
    public List<DocVO> getDocList() {
        LambdaQueryWrapper<Doc> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Doc::getSort);
        List<Doc> docList = docMapper.selectList(queryWrapper);

        // 如果查询结果为空，则返回一个空的List
        if (CollectionUtil.isEmpty(docList)) {
            return new ArrayList<DocVO>();
        }

        List<DocVO> docVOList = new ArrayList<>();
        for (Doc doc : docList) {
            DocVO docVO = BeanUtil.copyProperties(doc, DocVO.class);

            docVOList.add(docVO);
        }

        return docVOList;
    }




    /**
     * 保存文档
     * @param docVO 文档信息
     * @return 保存结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveDoc(DocVO docVO) {
        if (docVO == null) {
            throw new CustomException(ResponseCode.NO_PARAM);
        }
        if (docVO.getBookId() == null) {
            throw new CustomException(400, "请选择所属书籍");
        }

        Doc doc = BeanUtil.copyProperties(docVO, Doc.class);
        Content content = BeanUtil.copyProperties(docVO, Content.class);

        try {
            if (doc.getId() == null) {
                // id为空，执行插入操作
                int inserted = docMapper.insert(doc);
                content.setId(doc.getId());
                int i = contentMapper.insert(content);

                return  inserted > 0 && i > 0;
            } else {
                // id不为空，先查询是否存在该记录
                Doc selectById = docMapper.selectById(doc.getId());
                Optional.ofNullable(selectById)
                        .orElseThrow(() -> new CustomException(500, "该文档不存在"));
                // 执行更新操作
                int updated = docMapper.updateById(doc);
                int i = contentMapper.updateById(content);
                if (i == 0) {
                    content.setId(doc.getId());
                    i = contentMapper.insert(content);
                }

                return updated > 0 && i > 0;
            }
        } catch (Exception e) {
            throw new CustomException(ResponseCode.SYSTEM_ERROR);
        }
    }




    /**
     * 删除文档
     * @param idList 文档ID
     * @return 删除结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteDoc(List<String> idList) {
        if (CollectionUtil.isEmpty(idList)) {
            throw new CustomException(ResponseCode.NO_PARAM);
        }

        int deleteById = docMapper.deleteBatchIds(idList);
        if (deleteById <= 0) {
            throw new CustomException(500, "删除失败");
        }

        return true;
    }



    /**
     * 搜索文档
     * @param keyWord 关键字
     * @return 搜索结果
     */
    @Override
    public List<DocVO> searchDoc(String keyWord) {
        LambdaQueryWrapper<Doc> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Doc::getDocName, keyWord);
        List<Doc> docList = docMapper.selectList(queryWrapper);

        // 如果查询结果为空，则返回一个空的List
        if (CollectionUtil.isEmpty(docList)) {
            return new ArrayList<>();
        }

        List<DocVO> docVOList = new ArrayList<>();
        for (Doc doc : docList) {
            DocVO docVO = BeanUtil.copyProperties(doc, DocVO.class);

            docVOList.add(docVO);
        }

        return docVOList;
    }



    /**
     * 获取文档详情
     * @param bookId 书籍id
     * @return 文档列表
     */
    @Override
    public List<DocVO> getDocByBookId(Integer bookId) {
        if (bookId == null) {
            throw new CustomException(ResponseCode.NO_PARAM);
        }

        LambdaQueryWrapper<Doc> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Doc::getBookId, bookId);
        List<Doc> docList = docMapper.selectList(queryWrapper);
        // 如果查询结果为空，则返回一个空的List
        if (CollectionUtil.isEmpty(docList)) {
            return new ArrayList<>();
        }

        List<DocVO> docVOList = new ArrayList<>();
        for (Doc doc : docList) {
            DocVO docVO = BeanUtil.copyProperties(doc, DocVO.class);

            docVOList.add(docVO);
        }

        return docVOList;
    }
}




