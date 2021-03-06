package com.zeblog.dao;

import com.zeblog.bo.ArticleBo;
import com.zeblog.entity.Article;
import com.zeblog.entity.ArticleCategory;
import com.zeblog.entity.ArticleTag;

import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer articleId);

    int insert(ArticleBo article);

    ArticleBo selectSimpleArticleByArticleId(Integer articleId);

    ArticleBo selectArticleByArticleId(Integer articleId);

    List<ArticleBo> selectAllArticleByAuthorId(Integer authorId);

    List<ArticleBo> selectAllArticle();

    List<ArticleBo> selectHomeArticle();

    List<ArticleBo> selectArticleByPage(Integer skip);

    List<ArticleBo> selectArticleByTagId(Integer tagId);

    List<ArticleBo> selectArticleByCategoryId(Integer categoryId);

    int updateByPrimaryKey(ArticleBo article);

    List<ArticleTag> selectArticleTagByArticleId(Integer articleId);

    List<ArticleCategory> selectArticleCategoryByArticleId(Integer articleId);

    Integer selectArticleCount();

    void updateArticleReadTimes(Integer articleId);

    List<ArticleBo> selectArticleOrderByReadTimes();
}