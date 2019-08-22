package com.zeblog.service;

import com.zeblog.bo.CommentBo;
import com.zeblog.common.ServerResponse;
import com.zeblog.entity.Comment;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: Hezepeng
 * @email: hezepeng96@foxmail.com
 * @date: 2019-08-19 15:49
 */
public interface CommentService {
    ServerResponse<Comment> addComment(HttpServletRequest request, Comment comment);

    ServerResponse deleteComment(HttpServletRequest request, Comment comment);

    ServerResponse updateComment(HttpServletRequest request, Comment comment);

    ServerResponse<List<CommentBo>> selectCommentByArticleId(Integer articleId);

    ServerResponse<List<CommentBo>> selectCommentByType(String commentType);


}
