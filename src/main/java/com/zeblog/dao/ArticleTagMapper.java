package com.zeblog.dao;

import com.zeblog.entity.ArticleTag;
import java.util.List;

public interface ArticleTagMapper {
    int deleteByPrimaryKey(Integer articleTagId);

    int deleteByArticleId(Integer articleId);


    int insert(ArticleTag record);

    ArticleTag selectByPrimaryKey(Integer articleTagId);

    List<ArticleTag> selectAll();

    int updateByPrimaryKey(ArticleTag record);
}