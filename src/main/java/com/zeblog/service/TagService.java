package com.zeblog.service;

import com.zeblog.common.ServerResponse;
import com.zeblog.entity.ArticleTag;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: Hezepeng
 * @email: hezepeng96@foxmail.com
 * @date: 2019-07-31 16:46
 */
public interface TagService {
    ServerResponse<List<ArticleTag>> getUserTag(HttpServletRequest request);

    ServerResponse<ArticleTag> addUserTag(HttpServletRequest request, ArticleTag tag);

    ServerResponse deleteUserTag(HttpServletRequest request,ArticleTag tag);

    ServerResponse updateUserTag(HttpServletRequest request,ArticleTag tag);
}
