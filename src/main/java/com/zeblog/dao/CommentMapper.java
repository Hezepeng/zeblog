package com.zeblog.dao;

import com.zeblog.bo.CommentBo;
import com.zeblog.entity.Comment;
import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(Comment record);

    Comment selectByPrimaryKey(Integer commentId);

    List<CommentBo> selectCommentByArticleId(Integer articleId);

    List<CommentBo> selectCommentByType(String commentType);

    List<Comment> selectAll();

    int updateByPrimaryKey(Comment record);
}