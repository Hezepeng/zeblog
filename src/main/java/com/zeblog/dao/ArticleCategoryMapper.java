package com.zeblog.dao;

import com.zeblog.entity.ArticleCategory;
import java.util.List;

public interface ArticleCategoryMapper {
    int deleteByPrimaryKey(Integer articleCategoryId);

    int deleteByArticleId(Integer articleId);

    int insert(ArticleCategory record);

    ArticleCategory selectByPrimaryKey(Integer articleCategoryId);

    List<ArticleCategory> selectAll();

    int updateByPrimaryKey(ArticleCategory record);
}