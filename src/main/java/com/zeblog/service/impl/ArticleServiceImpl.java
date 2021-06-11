package com.zeblog.service.impl;

import com.zeblog.bo.ArticleBo;
import com.zeblog.bo.ArticleByPageBo;
import com.zeblog.common.Const;
import com.zeblog.common.ServerResponse;
import com.zeblog.dao.ArticleCategoryMapper;
import com.zeblog.dao.ArticleMapper;
import com.zeblog.dao.ArticleTagMapper;
import com.zeblog.dao.UserMapper;
import com.zeblog.entity.*;
import com.zeblog.service.ArticleService;
import com.zeblog.util.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author: Hezepeng
 * @email: hezepeng96@foxmail.com
 * @date: 2019-07-28 23:03
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleTagMapper articleTagMapper;
    @Autowired
    ArticleCategoryMapper articleCategoryMapper;

    @Override
    public ServerResponse<List<ArticleBo>> getAllArticles(HttpServletRequest request) {
        Integer userId = TokenUtil.getUserIdFromRequest(request);
        return ServerResponse.createBySuccess(articleMapper.selectAllArticleByAuthorId(userId));
    }

    @Override
    public ServerResponse<ArticleBo> getArticleById(Integer articleId) {
        articleMapper.updateArticleReadTimes(articleId);
        return ServerResponse.createBySuccess(articleMapper.selectArticleByArticleId(articleId));
    }

    @Override
    public ServerResponse<List<ArticleBo>> getHomeArticle() {
        return ServerResponse.createBySuccess(articleMapper.selectHomeArticle());
    }

    @Override
    public ServerResponse<ArticleByPageBo> getArticleByPage(Integer page) {
        page = page < 1 ? 1 : page;
        page -= 1;
        ArticleByPageBo articlePageBo = new ArticleByPageBo();
        articlePageBo.setArticleList(articleMapper.selectArticleByPage(page * 5));
        articlePageBo.setArticleTotalCount(articleMapper.selectArticleCount());
        for (ArticleBo article : articlePageBo.getArticleList()) {
            int subIndex = Math.min(article.getHtmlContent().length(), 1500);
            article.setHtmlContent(article.getHtmlContent().substring(0, subIndex));
        }
        return ServerResponse.createBySuccess(articlePageBo);
    }

    @Override
    public ServerResponse<List<ArticleBo>> getArticleByTag(Integer tagId) {
        return ServerResponse.createBySuccess(articleMapper.selectArticleByTagId(tagId));
    }

    @Override
    public ServerResponse<List<ArticleBo>> getArticleByCategory(Integer categoryId) {
        return ServerResponse.createBySuccess(articleMapper.selectArticleByTagId(categoryId));
    }

    @Override
    public ServerResponse<Map<String, String>> uploadImage(HttpServletRequest request, MultipartFile file) {
        // 获取webapp所在的物理路径
        String rootPath = request.getSession().getServletContext().getRealPath("");
        String savePath = "";
        if (!file.isEmpty()) {
            //生成uuid作为文件名
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            //获取文件类型
            String contentType = file.getContentType();
            System.out.println(contentType);
            //获得文件类型信息 : image/png
            if (StringUtils.isEmpty(contentType)) {
                throw new NullPointerException();
            }
            //获取图片类型
            String imageType = contentType.substring(contentType.indexOf("/") + 1);
            savePath = rootPath + "images/" + uuid + "." + imageType;
            System.out.println(savePath);
            try {
                file.transferTo(new File(savePath));
            } catch (IOException ex) {
                ex.printStackTrace();
                ServerResponse.createByErrorMessage("文件保存时发生IO异常");
            } catch (NullPointerException ex) {
                ServerResponse.createByErrorMessage("获取文件类型失败");
            }
            Map<String, String> resultMap = new HashMap<>(10);
            resultMap.put("url", savePath);
            return ServerResponse.createBySuccess("上传图片成功", resultMap);
        }
        return ServerResponse.createByErrorMessage("未获取到上传文件");
    }

    @Override
    public ServerResponse<ArticleBo> addArticle(HttpServletRequest request, ArticleBo article) {
        Integer userId = TokenUtil.getUserIdFromRequest(request);
        if (article != null) {
            article.setAuthorId(userId);
            article.setCreateTime(new Date());
            article.setReadTimes(0);
            article.setThumbsUpTimes(0);
            article.setIsDelete(false);
        } else {
            return ServerResponse.createByErrorMessage("未接收到文章信息");
        }
        int effectRow = articleMapper.insert(article);
        // 保存文章标签
        for (ArticleTag tag : article.getTags()) {
            tag.setArticleId(article.getArticleId());
            articleTagMapper.insert(tag);
        }
        // 保存文章分类
        for (ArticleCategory articleCategory : article.getCategories()) {
            articleCategory.setArticleId(article.getArticleId());
            articleCategoryMapper.insert(articleCategory);
        }
        if (effectRow == 0) {
            return ServerResponse.createByErrorMessage("发布文章失败");
        }
//        String msg = article.getState() == 0 ? "文章已保存到草稿箱" : "发布文章成功";
        String msg = article.getState() == 0 ? "文章已按HTML形式发表" : "文章已按MarkDown形式发表";
        return ServerResponse.createBySuccess(msg, article);
    }

    @Override
    public ServerResponse<ArticleBo> updateArticle(HttpServletRequest request, ArticleBo article) {
        if (article != null) {
            article.setUpdateTime(new Date());
        } else {
            return ServerResponse.createByErrorMessage("未接收到文章信息");
        }
        int effectRow = articleMapper.updateByPrimaryKey(article);
        // 删除旧的的文章标签
        articleTagMapper.deleteByArticleId(article.getArticleId());
        // 保存新的文章标签
        for (ArticleTag tag : article.getTags()) {
            tag.setArticleId(article.getArticleId());
            effectRow += articleTagMapper.insert(tag);
        }
        // 删除旧的文章分类
        articleCategoryMapper.deleteByArticleId(article.getArticleId());
        // 保存新的文章分类
        for (ArticleCategory articleCategory : article.getCategories()) {
            articleCategory.setArticleId(article.getArticleId());
            effectRow += articleCategoryMapper.insert(articleCategory);
        }
        if (effectRow == 0) {
            return ServerResponse.createByErrorMessage("本次更新未做任何更改");
        }
        String msg = article.getState() == 0 ? "文章已更新并保存到草稿箱" : "文章信息已更新";
        return ServerResponse.createBySuccess(msg, article);
    }

    @Override
    public ServerResponse<ArticleBo> deleteArticle(HttpServletRequest request, ArticleBo article) {
        if (article != null) {
            articleMapper.deleteByPrimaryKey(article.getArticleId());
            return ServerResponse.createBySuccessMessage("该文章已删除");
        }
        return ServerResponse.createByErrorMessage("未接收到文章信息");
    }

    @Override
    public ServerResponse<List<ArticleBo>> getHotArticle() {
        return ServerResponse.createBySuccess(articleMapper.selectArticleOrderByReadTimes());
    }
}
