package com.zeblog.service.impl;

import com.zeblog.bo.CommentReplyBo;
import com.zeblog.common.Const;
import com.zeblog.common.ServerResponse;
import com.zeblog.dao.CommentReplyMapper;
import com.zeblog.dao.UserMapper;
import com.zeblog.entity.CommentReply;
import com.zeblog.entity.User;
import com.zeblog.service.ReplyService;
import com.zeblog.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Hezepeng
 * @email: hezepeng96@foxmail.com
 * @date: 2019/8/23 13:38
 */
@Service
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private CommentReplyMapper commentReplyMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse<CommentReplyBo> addReply(HttpServletRequest request, CommentReply commentReply) {
        try {
            int userId = TokenUtil.getUserIdFromRequest(request);
            commentReply.setFromUserId(userId);
            int effectRow = commentReplyMapper.insert(commentReply);
            if (effectRow == 0) {
                return ServerResponse.createByErrorMessage("回复评论失败");
            }
            CommentReplyBo commentReplyBo = commentReplyMapper.selectByPrimaryKey(commentReply.getCommentReplyId());
            return ServerResponse.createBySuccess("评论添加成功", commentReplyBo);
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("添加评论时发生异常");
        }
    }

    @Override
    public ServerResponse deleteReply(HttpServletRequest request, CommentReply commentReply) {
        try {
            int userId = TokenUtil.getUserIdFromRequest(request);
            User user = userMapper.selectByPrimaryKey(userId);
            CommentReplyBo commentReplyBo = commentReplyMapper.selectByPrimaryKey(commentReply.getCommentReplyId());
            // 防止抓包删除非本人评论(管理员除外)
            if(userId != commentReplyBo.getFromUserId() && !user.getRole().equals(Const.ADMIN_ROLE)){
                return ServerResponse.createByErrorMessage("没有合法权限");
            }
            int effectRow = commentReplyMapper.deleteByPrimaryKey(commentReply.getCommentReplyId());
            if (effectRow == 0) {
                return ServerResponse.createByErrorMessage("回复删除失败");
            }
            return ServerResponse.createBySuccessMessage("评论已删除");
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("删除评论时发生异常");
        }
    }

    @Override
    public ServerResponse updateReply(HttpServletRequest request, CommentReply commentReply) {
        return null;
    }
}
