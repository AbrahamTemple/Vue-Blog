package com.cloud.mdblog.rest;


import com.cloud.mdblog.jwt.AccessAuth;
import com.cloud.mdblog.service.ArticleAttachmentService;
import com.github.pagehelper.PageInfo;
import com.cloud.mdblog.entity.ArticleAttachment;
import com.cloud.mdblog.service.impl.ArticleAttachmentServiceImpl;
import com.cloud.mdblog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/article-attachment")
public class ArticleAttachmentController {
    @Autowired
    private ArticleAttachmentService articleAttachmentService;

    @AccessAuth
    @PostMapping("/create")
    public Result create(@RequestBody ArticleAttachment articleAttachmen){
        articleAttachmentService.create(articleAttachmen);
        return Result.ok(articleAttachmen);
    }

    @AccessAuth
    @PostMapping("/delete")
    public Result delete(Integer id){
        articleAttachmentService.delete(id);
        return Result.ok();
    }

    @AccessAuth
    @PostMapping("/update")
    public Result update(@RequestBody ArticleAttachment articleAttachmen){

        System.out.println("我是update");
        System.out.println("articleAttachmen====>"+articleAttachmen);
        articleAttachmentService.update(articleAttachmen);
        return Result.ok(articleAttachmen);
    }
    @PostMapping("/query")
    public Map query(@RequestBody ArticleAttachment articleAttachmen){
        PageInfo<ArticleAttachment> pageInfo = articleAttachmentService.query(articleAttachmen);
        System.out.println(pageInfo.getList());
        return Result.ok(pageInfo);
    }

    @PostMapping("/detail")
    public Result detail(Integer id){
        ArticleAttachment detail = articleAttachmentService.detail(id);
        return Result.ok(detail);
    }

    @PostMapping("/count")
    public Result count(@RequestBody ArticleAttachment articleAttachmen){
        int count = articleAttachmentService.count(articleAttachmen);
        return Result.ok(count);
    }
}

