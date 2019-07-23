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
    public static final Long TOKEN_VALID_TIME = 3600000L;

    /**
     * token secret
     */
    public static final String TOKEN_SECRET = "zeblog";

    public static final String TOKEN_ISSUER = "zeblog";

}
