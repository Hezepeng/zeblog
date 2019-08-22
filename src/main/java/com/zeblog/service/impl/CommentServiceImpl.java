package com.zeblog.service.impl;

import com.zeblog.common.ServerResponse;
import com.zeblog.dao.CommentMapper;
import com.zeblog.entity.Comment;
import com.zeblog.service.CommentService;
import com.zeblog.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: Hezepeng
 * @email: hezepeng96@foxmail.com
 * @date: 2019-08-19 15:50
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public ServerResponse<Comment> addComment(HttpServletRequest request, Comment comment) {
        try {
            int userId = TokenUtil.getUserIdFromRequest(request);
            comment.setFromUserId(userId);
            int effectRow = commentMapper.insert(comment);
            if (effectRow == 0) {
                return ServerResponse.createByErrorMessage("评论添加失败");
            }
            comment = commentMapper.selectByPrimaryKey(comment.getCommentId());
            return ServerResponse.createBySuccess("评论添加成功", comment);
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("添加评论时发生异常");
        }
    }

    @Override
    public ServerResponse deleteComment(HttpServletRequest request, Comment comment) {
        try {
            int effectRow = commentMapper.deleteByPrimaryKey(comment.getCommentId());
            if (effectRow == 0) {
                return ServerResponse.createByErrorMessage("评论删除失败");
            }
            return ServerResponse.createBySuccessMessage("该评论已成功删除");
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("删除评论时发生异常");
        }
    }

    @Override
    public ServerResponse updateComment(HttpServletRequest request, Comment comment) {
        try {
            int effectRow = commentMapper.updateByPrimaryKey(comment);
            if (effectRow == 0) {
                return ServerResponse.createByErrorMessage("评论更新失败");
            }
            comment = commentMapper.selectByPrimaryKey(comment.getCommentId());
            return ServerResponse.createBySuccess("评论信息更新成功", comment);
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("更新评论时发生异常");
        }
    }

    @Override
    public ServerResponse<List<Comment>> selectCommentByArticleId(Integer articleId) {
        return ServerResponse.createBySuccess(commentMapper.selectByArticleId(articleId));
    }

    @Override
    public ServerResponse selectCommentByType() {
        return null;
    }
}
