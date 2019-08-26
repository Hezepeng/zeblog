package com.zeblog.dao;

import com.zeblog.bo.CommentReplyBo;
import com.zeblog.entity.CommentReply;
import java.util.List;

public interface CommentReplyMapper {
    int deleteByPrimaryKey(Integer commentReplyId);

    int insert(CommentReply record);

    CommentReplyBo selectByPrimaryKey(Integer commentReplyId);

    List<CommentReply> selectAll();

    List<CommentReplyBo> selectCommentReplyByCommentId(Integer commentId);

    int updateByPrimaryKey(CommentReply record);
}