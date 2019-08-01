package com.zeblog.service.impl;

import com.zeblog.common.ServerResponse;
import com.zeblog.dao.CategoryMapper;
import com.zeblog.entity.Category;
import com.zeblog.service.CategoryService;
import com.zeblog.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: Hezepeng
 * @email: hezepeng96@foxmail.com
 * @date: 2019-07-31 16:50
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ServerResponse<List<Category>> getUserCategory(HttpServletRequest request) {
        Integer userId = TokenUtil.getUserIdFromRequest(request);
        return ServerResponse.createBySuccess(categoryMapper.selectUserCategory(userId));
    }

    @Override
    public ServerResponse addUserCategory(HttpServletRequest request, Category category) {
        Category existCategory = categoryMapper.selectByUserIdAndCategoryName(category);
        if (existCategory != null) {
            return ServerResponse.createByErrorMessage("该类目已存在，请勿重复添加");
        }

        int effectRow;
        try {
            effectRow = categoryMapper.insert(category);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("发生异常，类目添加失败");
        }
        if (effectRow == 0) {
            return ServerResponse.createByErrorMessage("类目添加失败");
        }
        return ServerResponse.createBySuccessMessage("添加成功");
    }

    @Override
    public ServerResponse deleteUserCategory(HttpServletRequest request, Category category) {
        Category existCategory = categoryMapper.selectByPrimaryKey(category.getCategoryId());
        if (existCategory == null) {
            return ServerResponse.createByErrorMessage("要删除的类目不存在");
        }
        int effectRow;
        try {
            effectRow = categoryMapper.deleteByPrimaryKey(category.getCategoryId());
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("发生异常，类目删除失败");
        }
        if (effectRow == 0) {
            return ServerResponse.createByErrorMessage("类目删除失败");
        }
        return ServerResponse.createBySuccessMessage("删除成功");
    }

    @Override
    public ServerResponse updateUserCategory(HttpServletRequest request, Category category) {
        Category existCategory = categoryMapper.selectByPrimaryKey(category.getCategoryId());
        if (existCategory == null) {
            return ServerResponse.createByErrorMessage("要更新的类目不存在");
        }
        int effectRow;
        try {
            effectRow = categoryMapper.updateByPrimaryKey(category);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("发生异常，类目更新失败");
        }
        if (effectRow == 0) {
            return ServerResponse.createByErrorMessage("类目更新失败");
        }
        return ServerResponse.createBySuccessMessage("添加成功");


    }
}
