package com.zeblog.service;

import com.zeblog.common.ServerResponse;
import com.zeblog.entity.Tag;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: Hezepeng
 * @email: hezepeng96@foxmail.com
 * @date: 2019-07-31 16:46
 */
public interface TagService {
    ServerResponse<List<Tag>> getUserTag(HttpServletRequest request);

    ServerResponse<Tag> addUserTag(HttpServletRequest request, Tag tag);

    ServerResponse deleteUserTag(HttpServletRequest request,Tag tag);

    ServerResponse updateUserTag(HttpServletRequest request,Tag tag);
}
