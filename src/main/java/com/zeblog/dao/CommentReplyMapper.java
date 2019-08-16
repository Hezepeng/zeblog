package com.zeblog.dao;

import com.zeblog.entity.CommentReply;
import java.util.List;

public interface CommentReplyMapper {
    int deleteByPrimaryKey(Integer commentReplyId);

    int insert(CommentReply record);

    CommentReply selectByPrimaryKey(Integer commentReplyId);

    List<CommentReply> selectAll();

    int updateByPrimaryKey(CommentReply record);
}