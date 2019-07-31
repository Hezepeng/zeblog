package com.zeblog.dao;

import com.zeblog.entity.Category;

import java.util.List;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(Category record);

    Category selectByPrimaryKey(Integer categoryId);

    Category selectByUserIdAndCategoryName(Category category);

    List<Category> selectAll();

    int updateByPrimaryKey(Category record);

    List<Category> selectUserCategory(Integer userId);
}