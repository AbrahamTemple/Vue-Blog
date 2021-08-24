package com.cloud.mdblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.mdblog.entity.ArticleTag;
import com.github.pagehelper.PageInfo;


public interface ArticleTagService extends IService<ArticleTag> {
    int create(ArticleTag articleTag);
    int delete(Integer id);
    int update(ArticleTag articleTag);
    PageInfo<ArticleTag> query(ArticleTag articleTag);
    PageInfo<ArticleTag> getArticleId(ArticleTag articleTag);
    ArticleTag detail(Integer id);
    int count(ArticleTag articleTag);
}
