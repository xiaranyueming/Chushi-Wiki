package com.littlemonster.chushiwiki.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.littlemonster.chushiwiki.entity.domain.Category;
import com.littlemonster.chushiwiki.entity.vo.CategoryVO;

import java.util.List;

/**
* @author white-zhou
* @description 针对表【category(分类表)】的数据库操作Service
* @createDate 2024-04-04 20:26:08
*/
public interface CategoryService extends IService<Category> {


    /**
     * 获取分类列表
     * @return 分类列表
     */
    List<CategoryVO> getCategoryList();


    /**
     * 保存分类
     * @param categoryVO 分类信息
     * @return 保存结果
     */
    boolean saveCategory(CategoryVO categoryVO);


    /**
     * 删除分类
     * @param categoryId 分类id
     * @return 删除结果
     */
    boolean deleteCategory(Long categoryId);


    /**
     * 搜索分类
     * @param keyWord 关键字
     * @return 分类列表
     */
    List<CategoryVO> searchCategory(String keyWord);
}
