package com.zeblog.service;

import com.zeblog.bo.ArticleBo;
import com.zeblog.bo.ArticleByPageBo;
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
    ServerResponse<List<ArticleBo>> getAllArticles(HttpServletRequest request);

    ServerResponse<ArticleBo> getArticleById(Integer articleId);

    ServerResponse<List<ArticleBo>> getHomeArticle();

    ServerResponse<ArticleByPageBo> getArticleByPage(Integer page);

    ServerResponse<List<ArticleBo>> getArticleByTag(Integer tagId);

    ServerResponse<List<ArticleBo>> getArticleByCategory(Integer categoryId);

    ServerResponse<Map<String ,String>> uploadImage(HttpServletRequest request, MultipartFile file);

    ServerResponse<ArticleBo> addArticle(HttpServletRequest request, ArticleBo article);

    ServerResponse<ArticleBo> updateArticle(HttpServletRequest request, ArticleBo article);

    ServerResponse<ArticleBo> deleteArticle(HttpServletRequest request, ArticleBo article);


    ServerResponse<List<ArticleBo>> getHotArticle();
}
