package com.littlemonster.chushiwiki.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.littlemonster.chushiwiki.common.ResponseCode;
import com.littlemonster.chushiwiki.entity.domain.Category;
import com.littlemonster.chushiwiki.entity.vo.CategoryVO;
import com.littlemonster.chushiwiki.exception.CustomException;
import com.littlemonster.chushiwiki.mapper.CategoryMapper;
import com.littlemonster.chushiwiki.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
* @author white-zhou
* @description 针对表【category(分类表)】的数据库操作Service实现
* @createDate 2024-04-04 20:26:08
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }



    /**
     * 获取分类列表
     * @return 分类列表
     */
    @Override
    public List<CategoryVO> getCategoryList() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Category::getSort);
        List<Category> categoryList = categoryMapper.selectList(queryWrapper);

        // 如果查询结果为空，则返回一个空的List
        if (CollectionUtil.isEmpty(categoryList)) {
            return new ArrayList<CategoryVO>();
        }

        List<CategoryVO> categoryVOList = new ArrayList<>();
        for (Category category : categoryList) {
            CategoryVO categoryVO = BeanUtil.copyProperties(category, CategoryVO.class);

            categoryVOList.add(categoryVO);
        }

        return categoryVOList;
    }




    /**
     * 保存分类
     * @param categoryVO 分类信息
     * @return 保存结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveCategory(CategoryVO categoryVO) {
        if (categoryVO == null) {
            throw new CustomException(ResponseCode.NO_PARAM);
        }

        Category category = BeanUtil.copyProperties(categoryVO, Category.class);
        if (category.getId() == null) {
            // id为空，执行插入操作
            return categoryMapper.insert(category) > 0;
        } else {
            // id不为空，先查询是否存在该记录
            Category selectById = categoryMapper.selectById(category.getId());
            Optional.ofNullable(selectById)
                    .orElseThrow(() -> new CustomException(500, "该分类不存在"));
            // 执行更新操作
            return categoryMapper.updateById(category) > 0;
        }
    }




    /**
     * 删除分类
     * @param categoryId 分类ID
     * @return 删除结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteCategory(Long categoryId) {
        if (categoryId == null) {
            throw new CustomException(ResponseCode.NO_PARAM);
        }

        int deleteById = categoryMapper.deleteById(categoryId);
        if (deleteById <= 0) {
            throw new CustomException(500, "删除失败");
        }

        return true;
    }



    /**
     * 搜索分类
     * @param keyWord 关键字
     * @return 搜索结果
     */
    @Override
    public List<CategoryVO> searchCategory(String keyWord) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Category::getCategoryName, keyWord);
        List<Category> categoryList = categoryMapper.selectList(queryWrapper);

        // 如果查询结果为空，则返回一个空的List
        if (CollectionUtil.isEmpty(categoryList)) {
            return new ArrayList<>();
        }

        List<CategoryVO> categoryVOList = new ArrayList<>();
        for (Category category : categoryList) {
            CategoryVO categoryVO = BeanUtil.copyProperties(category, CategoryVO.class);

            categoryVOList.add(categoryVO);
        }

        return categoryVOList;
    }
}




