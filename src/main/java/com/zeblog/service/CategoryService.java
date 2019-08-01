package com.zeblog.service;

import com.zeblog.common.ServerResponse;
import com.zeblog.entity.Category;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: Hezepeng
 * @email: hezepeng96@foxmail.com
 * @date: 2019-07-31 16:49
 */
public interface CategoryService {
    ServerResponse<List<Category>> getUserCategory(HttpServletRequest request);

    ServerResponse addUserCategory(HttpServletRequest request, Category category);

    ServerResponse deleteUserCategory(HttpServletRequest request,Category category);

    ServerResponse updateUserCategory(HttpServletRequest request,Category category);
}
