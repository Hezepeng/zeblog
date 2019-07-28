package com.zeblog.dao;

import com.zeblog.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    Article selectByPrimaryKey(Integer id);

    List<Article> selectAll();

    List<Article> selectByAuthorId(Integer authorId);

    int updateByPrimaryKey(Article record);
}