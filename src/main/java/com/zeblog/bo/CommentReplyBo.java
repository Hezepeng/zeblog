package com.zeblog.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.zeblog.entity.User;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author: Hezepeng
 * @email: hezepeng96@foxmail.com
 * @date: 2019-08-22 12:18
 */
@JsonInclude(JsonInclude.Include.NON_NULL)//保证序列化json的时候,如果是null的对象,key也会消失，只作用于该类的第一层属性本身

public class CommentReplyBo {

    private User fromUser;

    private User toUser;

    private Integer commentReplyId;

    private Integer commentId;

    private Integer replyId;

    private String replyType;

    private String content;

    private Integer fromUserId;

    private Integer toUserId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private Integer likeTimes;

    private Integer replyTimes;

    public Integer getCommentReplyId() {
        return commentReplyId;
    }

    public void setCommentReplyId(Integer commentReplyId) {
        this.commentReplyId = commentReplyId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public String getReplyType() {
        return replyType;
    }

    public void setReplyType(String replyType) {
        this.replyType = replyType == null ? null : replyType.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getLikeTimes() {
        return likeTimes;
    }

    public void setLikeTimes(Integer likeTimes) {
        this.likeTimes = likeTimes;
    }

    public Integer getReplyTimes() {
        return replyTimes;
    }

    public void setReplyTimes(Integer replyTimes) {
        this.replyTimes = replyTimes;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }
}
