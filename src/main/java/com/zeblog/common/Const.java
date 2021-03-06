package com.zeblog.common;

/**
 * @author: Hezepeng
 * @email: hezepeng96@foxmail.com
 * @date: 2019-07-19 23:13
 */
public class Const {

    /**
     * 当前用户的session属性名
     */
    public static final String CURRENT_USER = "currentUser";

    /**
     * 管理员角色名
     */
    public static final String ADMIN_ROLE = "admin";

    /**
     * token有效期 3600秒
     */
    public static final Long TOKEN_VALID_TIME = 3*24*3600000L;

    /**
     * token secret
     */
    public static final String TOKEN_SECRET = "zeblog";

    /**
     * token 颁发者
     */
    public static final String TOKEN_ISSUER = "zeblog";

    /**
     * Headers中储存Token信息的字段名
     */
    public static final String TOKEN_HEADER_NAME = "X-Token";

    /**
     * 评论类型-文章评论
     */
    public static final String COMMENT_TYPE_ARTICLE = "article";

    /**
     * 评论类型-网站留言
     */
    public static final String COMMENT_TYPE_MESSAGE = "message";


}
