package com.zeblog.service;

import com.zeblog.common.ServerResponse;
import com.zeblog.entity.Article;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author: Hezepeng
 * @email: hezepeng96@foxmail.com
 * @date: 2019-07-28 23:01
 */
public interface ArticleService {
    ServerResponse<List<Article>> getAllArticles(HttpServletRequest request);

    ServerResponse<Article> getArticleById(Integer articleId);


    ServerResponse<Map<String ,String>> uploadImage(HttpServletRequest request, MultipartFile file);

    ServerResponse addArticle(HttpServletRequest request, Article article);


}
