package com.cloud.mdblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.mdblog.entity.ArticleAttachment;
import com.cloud.mdblog.mapper.ArticleAttachmentMapper;
import com.cloud.mdblog.service.ArticleAttachmentService;
import com.cloud.mdblog.utils.Maps;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ArticleAttachmentServiceImpl extends ServiceImpl<ArticleAttachmentMapper, ArticleAttachment> implements ArticleAttachmentService {

    @Autowired
    private ArticleAttachmentMapper articleAttachmentMapper;

    @Override
    public int create(ArticleAttachment articleAttachment){
        return articleAttachmentMapper.create(articleAttachment);
    }

    @Override
    public int delete(Integer id){
        return articleAttachmentMapper.delete(Maps.build(id).getMap());
    }

    @Override
    public int update(ArticleAttachment articleAttachment){
        return articleAttachmentMapper.update(Maps.build(articleAttachment.getId()).beanToMapForUpdate(articleAttachment));
    }

    @Override
    public PageInfo<ArticleAttachment> query(ArticleAttachment articleAttachment){
        if (articleAttachment!=null && articleAttachment.getPage() != null){
            PageHelper.startPage(articleAttachment.getPage(),articleAttachment.getLimit());
        }
        List<ArticleAttachment> list = articleAttachmentMapper.query(Maps.build().beanToMap(articleAttachment));
        return new PageInfo<ArticleAttachment> (list);
    }

    @Override
    public ArticleAttachment detail(Integer id){
        return articleAttachmentMapper.detail(Maps.build(id).getMap());
    }

    @Override
    public int count(ArticleAttachment articleAttachment){
        return articleAttachmentMapper.count(Maps.build().beanToMap(articleAttachment));
    }

}
