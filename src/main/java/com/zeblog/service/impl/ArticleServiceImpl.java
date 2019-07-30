package com.zeblog.service.impl;

import com.zeblog.bo.ArticleBo;
import com.zeblog.common.Const;
import com.zeblog.common.ServerResponse;
import com.zeblog.dao.ArticleMapper;
import com.zeblog.dao.UserMapper;
import com.zeblog.entity.Article;
import com.zeblog.entity.User;
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

    @Override
    public ServerResponse<List<ArticleBo>> getAllArticles(HttpServletRequest request) {
        String token = request.getHeader(Const.TOKEN_HEADER_NAME);
        Integer userId =TokenUtil.getUserId(token);
        return ServerResponse.createBySuccess(articleMapper.selectAllArticleByAuthorId(userId));
    }

    @Override
    public ServerResponse<ArticleBo> getArticleById(Integer articleId) {
        return ServerResponse.createBySuccess(articleMapper.selectArticleByArticleId(articleId));
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
    public ServerResponse addArticle(HttpServletRequest request, Article article) {
        String token = request.getHeader(Const.TOKEN_HEADER_NAME);
        Integer userId =TokenUtil.getUserId(token);
        if(article!=null){
            article.setAuthorId(userId);
            article.setCreateTime(new Date());
            article.setReadTimes(0);
            article.setThumbsUpTimes(0);
            article.setIsDelete(false);
        }else{
            return ServerResponse.createByErrorMessage("未接收到文章信息");
        }
        int effectRow = articleMapper.insert(article);
        if (effectRow == 0) {
            return ServerResponse.createByErrorMessage("发布文章失败");
        }
        return ServerResponse.createBySuccessMessage("发布文章成功");
    }
}