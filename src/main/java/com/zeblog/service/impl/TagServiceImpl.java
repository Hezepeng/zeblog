package com.zeblog.service.impl;

import com.zeblog.common.ServerResponse;
import com.zeblog.dao.ArticleTagMapper;
import com.zeblog.entity.ArticleTag;
import com.zeblog.service.TagService;
import com.zeblog.util.TokenUtil;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: Hezepeng
 * @email: hezepeng96@foxmail.com
 * @date: 2019-07-31 17:09
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Override
    public ServerResponse<List<ArticleTag>> getUserTag(HttpServletRequest request) {
        Integer userId= TokenUtil.getUserIdFromRequest(request);
        return null;
    }

    @Override
    public ServerResponse<ArticleTag> addUserTag(HttpServletRequest request, ArticleTag tag) {
        return null;
    }

    @Override
    public ServerResponse deleteUserTag(HttpServletRequest request, ArticleTag tag) {
        return null;
    }

    @Override
    public ServerResponse updateUserTag(HttpServletRequest request, ArticleTag tag) {
        return null;
    }
}
