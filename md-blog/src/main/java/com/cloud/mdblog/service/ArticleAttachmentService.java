package com.cloud.mdblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.mdblog.entity.ArticleAttachment;
import com.github.pagehelper.PageInfo;

public interface ArticleAttachmentService extends IService<ArticleAttachment> {
    int create(ArticleAttachment articleAttachment);
    int delete(Integer id);
    int update(ArticleAttachment articleAttachment);
    PageInfo<ArticleAttachment> query(ArticleAttachment articleAttachment);
    ArticleAttachment detail(Integer id);
    int count(ArticleAttachment articleAttachment);
}
