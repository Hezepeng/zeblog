package com.zeblog.service.impl;

import com.zeblog.common.ServerResponse;
import com.zeblog.dao.TagMapper;
import com.zeblog.entity.Tag;
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
    private TagMapper tagMapper;

    @Override
    public ServerResponse<List<Tag>> getUserTag(HttpServletRequest request) {
        Integer userId= TokenUtil.getUserIdFromRequest(request);
        return ServerResponse.createBySuccess(tagMapper.selectUserTag(userId));
    }

    @Override
    public ServerResponse<Tag> addUserTag(HttpServletRequest request, Tag tag) {
        return null;
    }

    @Override
    public ServerResponse deleteUserTag(HttpServletRequest request, Tag tag) {
        return null;
    }

    @Override
    public ServerResponse updateUserTag(HttpServletRequest request, Tag tag) {
        return null;
    }
}
