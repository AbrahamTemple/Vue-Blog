package com.cloud.mdblog.rest;


import com.cloud.mdblog.jwt.AccessAuth;
import com.cloud.mdblog.service.ArticleTagService;
import com.github.pagehelper.PageInfo;
import com.cloud.mdblog.entity.ArticleTag;
import com.cloud.mdblog.service.impl.ArticleTagServiceImpl;
import com.cloud.mdblog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/articleArticleTag")
public class ArticleTagController {

    @Autowired
    private ArticleTagService articleTagService;

    @AccessAuth
    @PostMapping("/create")
    public Result create(@RequestBody ArticleTag articleTag){
        articleTagService.create(articleTag);
        return Result.ok(articleTag);
    }

    @AccessAuth
    @PostMapping("/delete")
    public Result delete(Integer id){
        articleTagService.delete(id);
        return Result.ok();
    }

    @AccessAuth
    @PostMapping("/update")
    public Result update(@RequestBody ArticleTag articleTag){
        articleTagService.update(articleTag);
        return Result.ok(articleTag);
    }
    @PostMapping("/query")
    public Map query(@RequestBody ArticleTag articleTag){
        PageInfo<ArticleTag> pageInfo = articleTagService.query(articleTag);
        return Result.ok(pageInfo);
    }


    @PostMapping("/detail")
    public Result detail(Integer id){
        ArticleTag detail = articleTagService.detail(id);
        return Result.ok(detail);
    }

    @PostMapping("/count")
    public Result count(@RequestBody ArticleTag articleTag){
        int count = articleTagService.count(articleTag);
        return Result.ok(count);
    }
}

