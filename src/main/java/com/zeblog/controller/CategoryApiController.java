package com.zeblog.controller;

import com.zeblog.bo.CategoryBo;
import com.zeblog.common.ServerResponse;
import com.zeblog.dao.CategoryMapper;
import com.zeblog.entity.Category;
import com.zeblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: Hezepeng
 * @email: hezepeng96@foxmail.com
 * @date: 2019-07-31 17:27
 */
@Controller
@RequestMapping("api/category/")
public class CategoryApiController {

    @Autowired
    private CategoryService categoryService;

    @ResponseBody
    @RequestMapping(value = "getUserCategory", method = RequestMethod.GET)
    public ServerResponse<List<Category>> getUserCategory(HttpServletRequest request){
        return categoryService.getUserCategory(request);
    }

    @ResponseBody
    @RequestMapping(value = "getUserCategoryWithArticle", method = RequestMethod.GET)
    public ServerResponse<List<CategoryBo>> getUserCategoryWithArticle(HttpServletRequest request){
        return categoryService.getUserCategoryWithArticle(request);
    }

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ServerResponse addUserCategory(HttpServletRequest request, @RequestBody Category category){
        return categoryService.addUserCategory(request,category);
    }

    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ServerResponse updateUserCategory(HttpServletRequest request, @RequestBody Category category){
        return categoryService.updateUserCategory(request,category);
    }

    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ServerResponse deleteUserCategory(HttpServletRequest request, @RequestBody Category category){
        return categoryService.deleteUserCategory(request,category);
    }
}
