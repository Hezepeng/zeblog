package com.zeblog.dao;

import com.zeblog.bo.CategoryBo;
import com.zeblog.entity.ArticleCategory;
import com.zeblog.entity.Category;

import java.util.List;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(Category record);

    Category selectByPrimaryKey(Integer categoryId);

    Category selectByUserIdAndCategoryName(Category category);

    Category selectByUserIdAndCategoryId(Category category);

    List<Category> selectAll();

    List<ArticleCategory> selectUserCategoryArticle(Integer categoryId);

    int updateByPrimaryKey(Category record);

    List<Category> selectUserCategory(Integer userId);
}