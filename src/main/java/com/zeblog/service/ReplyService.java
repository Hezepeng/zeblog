package com.zeblog.service;

import com.zeblog.bo.CommentReplyBo;
import com.zeblog.common.ServerResponse;
import com.zeblog.entity.Comment;
import com.zeblog.entity.CommentReply;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Hezepeng
 * @email: hezepeng96@foxmail.com
 * @date: 2019/8/23 13:35
 */
public interface ReplyService {

    ServerResponse<CommentReplyBo> addReply(HttpServletRequest request, CommentReply commentReply);

    ServerResponse deleteReply(HttpServletRequest request, CommentReply commentReply);

    ServerResponse updateReply(HttpServletRequest request, CommentReply commentReply);

}
