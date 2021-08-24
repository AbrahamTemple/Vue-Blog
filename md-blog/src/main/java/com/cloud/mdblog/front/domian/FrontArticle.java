package com.cloud.mdblog.front.domian;

import com.cloud.mdblog.entity.Article;
import com.cloud.mdblog.entity.ArticleTag;
import com.cloud.mdblog.entity.Comment;
import com.cloud.mdblog.entity.Tag;
import com.cloud.mdblog.service.ArticleService;
import com.cloud.mdblog.utils.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.8.24
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
public abstract class FrontArticle implements Front{

    @Autowired
    private ArticleService articleService;

    public abstract Result create(Comment comment);

    public abstract Result getCommentArticleId(Integer articleId);

    public abstract Map getArticleTag(ArticleTag articleTag);

    public abstract Result all(Tag tag);

    @Override
    public Result getById(Integer id) {
        Article article = new Article();
        article.setId(id);
        article.setFront(true);
        Article detail = articleService.detail(article);
        return Result.ok(detail);
    }

    public Result getLikeArticle(Integer id){
        Article article = new Article();
        article.setId(id);
        article.setFront(false);
        Article detail = articleService.detail(article);
        return Result.ok(detail);
    }

    public Map getByIdChannel(Integer page){
        return Result.ok(articleService.getPage(null,page));
    }

    public Result getOlderArticle(){
        return Result.ok(articleService.orderBy());
    }

    public Result getNotice(Integer channelId){
        Article article = new Article();
        article.setChannelId(channelId);
        List<Article> list = articleService.getNotice(article);
        return Result.ok(list);
    }

    public Map pageArticleList(Integer number){
        return Result.ok(articleService.getPage(null,number));
    }

    public Result getById(Article article,Integer top){
        return Result.ok(articleService.top(article, top));
    }

    public Result getById(){
        return Result.ok(articleService.top());
    }

    public Result getRandomArticle(){
        return Result.ok(articleService.getRandomArticle());
    }

    public Map search(Article article){
        PageInfo<Article> pageInfo = articleService.query(article);
        return Result.ok(pageInfo);
    }

}
