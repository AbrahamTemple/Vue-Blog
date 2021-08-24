package com.cloud.mdblog.rest;

import com.cloud.mdblog.jwt.AccessAuth;
import com.cloud.mdblog.service.TagService;
import com.github.pagehelper.PageInfo;
import com.cloud.mdblog.entity.Tag;
import com.cloud.mdblog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @AccessAuth
    @PostMapping("/create")
    public Result create(@RequestBody Tag tag){
        tagService.create(tag);
        return Result.ok(tag);
    }

    @AccessAuth
    @PostMapping("/delete")
    public Result delete(Integer id){
        tagService.delete(id);
        return Result.ok();
    }

    @AccessAuth
    @PostMapping("/update")
    public Result update(@RequestBody Tag tag){

        System.out.println("我是update");
        System.out.println("tag====>"+tag);
        tagService.update(tag);
        return Result.ok(tag);
    }

    @PostMapping("/query")
    public Map query(@RequestBody Tag tag){
        PageInfo<Tag> pageInfo = tagService.query(tag);
        System.out.println(pageInfo.getList());
        return Result.ok(pageInfo);
    }


//    @PostMapping("/all")
//    public Result all(Tag tag){
//        return Result.ok(tagServiceImpl.all()) ;
//    }


    @PostMapping("/detail")
    public Result detail(Integer id){
        Tag detail = tagService.detail(id);
        return Result.ok(detail);
    }

    @PostMapping("/count")
    public Result count(@RequestBody Tag tag){
        int count = tagService.count(tag);
        return Result.ok(count);
    }
}
