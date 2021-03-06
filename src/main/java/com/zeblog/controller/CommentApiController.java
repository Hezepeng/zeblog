package com.zeblog.controller;

import com.zeblog.common.Const;
import com.zeblog.common.ServerResponse;
import com.zeblog.dao.CommentMapper;
import com.zeblog.entity.Comment;
import com.zeblog.interceptor.AdminInterceptor;
import com.zeblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Hezepeng
 * @email: hezepeng96@foxmail.com
 * @date: 2019-08-19 15:44
 */
@Controller
@RequestMapping("api/comment/")
public class CommentApiController {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addComment(HttpServletRequest request, @RequestBody Comment comment) {
        return commentService.addComment(request, comment);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    @AdminInterceptor
    public ServerResponse deleteComment(HttpServletRequest request, @RequestBody Comment comment) {
        return commentService.deleteComment(request, comment);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    @AdminInterceptor
    public ServerResponse updateComment(HttpServletRequest request, @RequestBody Comment comment) {
        return commentService.updateComment(request, comment);
    }

    @RequestMapping(value = "getCommentByArticleId", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse selectCommentByArticleId(Integer articleId) {
        return commentService.selectCommentByArticleId(articleId);
    }

    @RequestMapping(value = "getAllComment", method = RequestMethod.GET)
    @ResponseBody
    @AdminInterceptor
    public ServerResponse getAllComment() {
        return commentService.selectCommentByType(Const.COMMENT_TYPE_ARTICLE);
    }

    @RequestMapping(value = "getAllMessage", method = RequestMethod.GET)
    @ResponseBody
    @AdminInterceptor
    public ServerResponse selectAllMessage() {
        return commentService.selectCommentByType(Const.COMMENT_TYPE_MESSAGE);
    }
}
