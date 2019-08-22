package com.zeblog.dao;

import com.zeblog.entity.Comment;
import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(Comment record);

    Comment selectByPrimaryKey(Integer commentId);

    List<Comment> selectByArticleId(Integer articleId);

    List<Comment> selectAll();

    int updateByPrimaryKey(Comment record);
}