package com.cloud.mdblog.front.domian;

import com.cloud.mdblog.entity.Article;
import com.cloud.mdblog.entity.Channel;
import com.cloud.mdblog.service.ArticleService;
import com.cloud.mdblog.utils.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.8.24
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
@Service
public class FrontChannelService extends FrontChannel{

    @Autowired
    private ArticleService articleService;

    @Override
    public Map getChannelArticle(Article article) {
        PageInfo<Article> page = articleService.getPage(article,article.getPage());
        return Result.ok(page);
    }

    @Override
    public Result getChannelArticlePos(Integer channelId) {
        return Result.ok(articleService.getChannelPos(channelId));
    }

}
