package com.zeblog.controller;

import com.zeblog.bo.ArticleBo;
import com.zeblog.common.ServerResponse;
import com.zeblog.entity.Article;
import com.zeblog.entity.User;
import com.zeblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author: Hezepeng
 * @email: hezepeng96@foxmail.com
 * @date: 2019-07-28 23:00
 */

@Controller
@RequestMapping("api/article/")
public class ArticleApiController {
    @Autowired
    private ArticleService articleService;

    @ResponseBody
    @RequestMapping(value = "uploadImage", method = RequestMethod.POST)
    public ServerResponse<Map<String, String>> uploadImage(HttpServletRequest request,@RequestParam("image") MultipartFile file) {
        return articleService.uploadImage(request,file);
    }

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ServerResponse addArticle(HttpServletRequest request, @RequestBody ArticleBo article) {
        return articleService.addArticle(request,article);
    }

    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ServerResponse updateArticle(HttpServletRequest request, @RequestBody ArticleBo article) {
        return articleService.updateArticle(request,article);
    }

    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ServerResponse deleteArticle(HttpServletRequest request, @RequestBody ArticleBo article) {
        return articleService.deleteArticle(request,article);
    }

    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ServerResponse getAllArticles(HttpServletRequest request) {
        return articleService.getAllArticles(request);
    }

    @ResponseBody
    @RequestMapping(value = "getArticleById", method = RequestMethod.GET)
    public ServerResponse getArticleById(Integer articleId) {
        return articleService.getArticleById(articleId);
    }
}
