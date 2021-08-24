package com.cloud.mdblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.mdblog.entity.Article;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface ArticleService extends IService<Article> {
    int create(Article article);
    int delete(Integer id);
    int update(Article article);
    PageInfo<Article> query(Article article);
    List<Article> orderBy();
    List<Article> getChannelPos(Integer id);
    PageInfo<Article> getPage(Article article,Integer number);
    List<Article> getRandomArticle();
    List<Article> top(Article article,Integer top);
    List<Article> top();
    Article detail(Article a);
    List<Article> getNotice(Article article);
    int count(Article article);
}
