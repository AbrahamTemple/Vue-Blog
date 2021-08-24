package com.cloud.mdblog.rest;


import cn.hutool.http.HtmlUtil;
import com.cloud.mdblog.jwt.AccessAuth;
import com.cloud.mdblog.service.ArticleService;
import com.github.pagehelper.PageInfo;
import com.cloud.mdblog.entity.Article;
import com.cloud.mdblog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @AccessAuth
    @PostMapping("/create")
    public Result create(@RequestBody Article article){

        System.out.println("summary钱"+article.getSummary());
            //文章摘要
        if (StringUtils.isEmpty(article.getSummary())){
            int summaryLength = 200;
            String newString = HtmlUtil.cleanHtmlTag(article.getContent());
            //过滤字符串
            String regEx="[\n`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。， 、？]";
            String aa = " ";
            Pattern p = Pattern.compile(regEx);
            Matcher matcher = p.matcher(newString);
            String summaryText = matcher.replaceAll(aa).trim();

            if (summaryText.length() > summaryLength) {
                String summary = summaryText.substring(0, summaryLength);
                article.setSummary(summary);
            } else {
                article.setSummary(summaryText);
            }

        }
        articleService.create(article);

        return Result.ok(article);
    }

    @AccessAuth
    @PostMapping("/delete")
    public Result delete(Integer id){
        articleService.delete(id);
        return Result.ok();
    }

    @AccessAuth
    @PostMapping("/update")
    public Result update(@RequestBody Article article){
        System.out.println("我是update");
        System.out.println("article====>"+article);
        articleService.update(article);
        return Result.ok(article);
    }


    @PostMapping("/query")
    public Map query(@RequestBody Article article){
        PageInfo<Article> pageInfo = articleService.query(article);
        System.out.println(pageInfo.getList());
        return Result.ok(pageInfo);
    }

    @PostMapping("/detail")
    public Result detail(Integer id){
        Article article = new Article();
        article.setId(id);
        article.setFront(false);
        Article detail = articleService.detail(article);
        return Result.ok(detail);
    }

    @PostMapping("/count")
    public Result count(@RequestBody Article article){
        int count = articleService.count(article);
        return Result.ok(count);
    }

}

