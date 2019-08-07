package com.zeblog.dao;

import com.zeblog.bo.ArticleBo;
import com.zeblog.entity.Article;
import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer articleId);

    int insert(ArticleBo article);

    ArticleBo selectSimpleArticleByArticleId(Integer articleId);

    ArticleBo selectArticleByArticleId(Integer articleId);

    List<ArticleBo> selectAllArticleByAuthorId(Integer authorId);

    List<ArticleBo> selectAllArticle();

    int updateByPrimaryKey(ArticleBo article);
}