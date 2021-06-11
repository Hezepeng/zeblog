package com.zeblog.bo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * @author Hezepeng
 * @email hezepeng96@foxmail.com
 * @date 2020/8/7 15:09
 * @description
 */
@JsonInclude(JsonInclude.Include.NON_NULL)//保证序列化json的时候,如果是null的对象,key也会消失，只作用于该类的第一层属性本身
public class ArticleByPageBo {
    private List<ArticleBo> articleList;
    private int articleTotalCount;

    public List<ArticleBo> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<ArticleBo> articleList) {
        this.articleList = articleList;
    }

    public int getArticleTotalCount() {
        return articleTotalCount;
    }

    public void setArticleTotalCount(int articleTotalCount) {
        this.articleTotalCount = articleTotalCount;
    }

    public ArticleByPageBo() {
    }
}
