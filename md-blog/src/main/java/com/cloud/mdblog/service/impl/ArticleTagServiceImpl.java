package com.cloud.mdblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.mdblog.entity.Article;
import com.cloud.mdblog.entity.ArticleTag;
import com.cloud.mdblog.entity.Channel;
import com.cloud.mdblog.entity.User;
import com.cloud.mdblog.mapper.ArticleMapper;
import com.cloud.mdblog.mapper.ArticleTagMapper;
import com.cloud.mdblog.mapper.ChannelMapper;
import com.cloud.mdblog.mapper.UserMapper;
import com.cloud.mdblog.service.ArticleTagService;
import com.cloud.mdblog.utils.Maps;
import com.cloud.mdblog.utils.UserNullUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {
    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ChannelMapper channelMapper;

    @Override
    public int create(ArticleTag articleTag){
        return articleTagMapper.create(articleTag);
    }

    @Override
    public int delete(Integer id){
        return articleTagMapper.delete(Maps.build(id).getMap());
    }

    @Override
    public int update(ArticleTag articleTag){
        return articleTagMapper.update(Maps.build(articleTag.getId()).beanToMapForUpdate(articleTag));
    }

    @Override
    public PageInfo<ArticleTag> query(ArticleTag articleTag){
        if (articleTag!=null && articleTag.getPage() != null){
            PageHelper.startPage(articleTag.getPage(),articleTag.getLimit());
        }
        List<ArticleTag> list = articleTagMapper.query(Maps.build().beanToMap(articleTag));
        return new PageInfo<> (list);
    }

    @Override
    public PageInfo<ArticleTag> getArticleId(ArticleTag articleTag){
        if (articleTag!=null && articleTag.getPage() != null){
            PageHelper.startPage(articleTag.getPage(),8);
        }
        List<ArticleTag> list = articleTagMapper.query(Maps.build(articleTag.getTagId()).beanToMap(articleTag));

        for (int i=0 ; i<list.size();i++){
            Article article = articleMapper.detail(Maps.build(list.get(i).getArticleId()).getMap());
            User user = userMapper.detail(Maps.build(article.getCreateUser()).getMap());
            Channel channel = channelMapper.detail(Maps.build(article.getChannelId()).getMap());
            if (user!=null){
                article.setUser(user);
            }else {
                article.setUser(UserNullUtils.userIsNull());
            }
            article.setChannel(channel);
            list.get(i).setArticle(article);
        }
        return new PageInfo<> (list);
    }

    @Override
    public ArticleTag detail(Integer id){
        return articleTagMapper.detail(Maps.build(id).getMap());
    }

    @Override
    public int count(ArticleTag articleTag){
        return articleTagMapper.count(Maps.build().beanToMap(articleTag));
    }
}


