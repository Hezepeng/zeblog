package com.zeblog.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.zeblog.entity.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author: Hezepeng
 * @email: hezepeng96@foxmail.com
 * @date: 2019-07-29 18:40
 */
@JsonInclude(JsonInclude.Include.NON_NULL)//保证序列化json的时候,如果是null的对象,key也会消失，只作用于该类的第一层属性本身

public class ArticleBo {

    private User author;

    private List<ArticleTag> tags;

    private List<ArticleCategory> categories;

    private Integer articleId;

    private String title;

    private Integer authorId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String originalType;

    private String codeStyle;

    private String markdownContent;

    private String htmlContent;

    private String markdownCatalog;

    private String htmlCatalog;

    private Integer readTimes;

    private Integer thumbsUpTimes;

    private Boolean isTop;

    private Integer topRank;

    private Boolean isDelete;

    private Integer state;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer id) {
        this.articleId = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOriginalType() {
        return originalType;
    }

    public void setOriginalType(String originalType) {
        this.originalType = originalType == null ? null : originalType.trim();
    }

    public String getCodeStyle() {
        return codeStyle;
    }

    public void setCodeStyle(String codeStyle) {
        this.codeStyle = codeStyle == null ? null : codeStyle.trim();
    }

    public String getMarkdownContent() {
        return markdownContent;
    }

    public void setMarkdownContent(String markdownContent) {
        this.markdownContent = markdownContent == null ? null : markdownContent.trim();
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent == null ? null : htmlContent.trim();
    }

    public String getMarkdownCatalog() {
        return markdownCatalog;
    }

    public void setMarkdownCatalog(String markdownCatalog) {
        this.markdownCatalog = markdownCatalog == null ? null : markdownCatalog.trim();
    }

    public String getHtmlCatalog() {
        return htmlCatalog;
    }

    public void setHtmlCatalog(String htmlCatalog) {
        this.htmlCatalog = htmlCatalog == null ? null : htmlCatalog.trim();
    }

    public Integer getReadTimes() {
        return readTimes;
    }

    public void setReadTimes(Integer readTimes) {
        this.readTimes = readTimes;
    }

    public Integer getThumbsUpTimes() {
        return thumbsUpTimes;
    }

    public void setThumbsUpTimes(Integer thumbsUpTimes) {
        this.thumbsUpTimes = thumbsUpTimes;
    }

    public Boolean getIsTop() {
        return isTop;
    }

    public void setIsTop(Boolean isTop) {
        this.isTop = isTop;
    }

    public Integer getTopRank() {
        return topRank;
    }

    public void setTopRank(Integer topRank) {
        this.topRank = topRank;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public List<ArticleCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<ArticleCategory> categories) {
        this.categories = categories;
    }

    public List<ArticleTag> getTags() {
        return tags;
    }

    public void setTags(List<ArticleTag> tags) {
        this.tags = tags;
    }
}
