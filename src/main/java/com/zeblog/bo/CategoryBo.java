package com.zeblog.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.zeblog.entity.ArticleCategory;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: Hezepeng
 * @email: hezepeng96@foxmail.com
 * @date: 2019-08-05 16:21
 */
@JsonInclude(JsonInclude.Include.NON_NULL)//保证序列化json的时候,如果是null的对象,key也会消失，只作用于该类的第一层属性本身

public class CategoryBo {

    private List<ArticleCategory> articles;

    private Integer categoryId;

    private Integer userId;

    private String categoryName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<ArticleCategory> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleCategory> articles) {
        this.articles = articles;
    }
}
