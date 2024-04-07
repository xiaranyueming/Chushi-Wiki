package com.littlemonster.chushiwiki.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.littlemonster.chushiwiki.common.ResponseCode;
import com.littlemonster.chushiwiki.entity.domain.Content;
import com.littlemonster.chushiwiki.entity.vo.ContentVO;
import com.littlemonster.chushiwiki.exception.CustomException;
import com.littlemonster.chushiwiki.mapper.ContentMapper;
import com.littlemonster.chushiwiki.mapper.DocMapper;
import com.littlemonster.chushiwiki.service.ContentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author white-zhou
* @description 针对表【content(文档内容表)】的数据库操作Service实现
* @createDate 2024-04-06 10:59:23
*/
@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content>
    implements ContentService{

    private final ContentMapper contentMapper;

    private final DocMapper docMapper;

    public ContentServiceImpl(ContentMapper contentMapper, DocMapper docMapper) {
        this.contentMapper = contentMapper;
        this.docMapper = docMapper;
    }




    /**
     * 获取文档内容
     * @param id 文档id
     * @return 文档内容
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ContentVO getContentDetail(Integer id) {
        if (id == null) {
            throw new CustomException(ResponseCode.NO_PARAM);
        }

        LambdaQueryWrapper<Content> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Content::getId, id)
                .select(Content::getContent);
        Content content = contentMapper.selectOne(queryWrapper);
        if (content == null) {
            throw new CustomException(500, "文档内容不存在");
        }

        boolean viewed = docMapper.viewDoc(id);
        if (!viewed) {
            throw new CustomException(500, "文档浏览次数更新失败");
        }

        return BeanUtil.copyProperties(content, ContentVO.class);
    }
}




