package com.cloud.mdblog.rest;


import com.cloud.mdblog.jwt.AccessAuth;
import com.cloud.mdblog.service.FriendLinkService;
import com.github.pagehelper.PageInfo;
import com.cloud.mdblog.entity.FriendLink;
import com.cloud.mdblog.service.impl.FriendLinkServiceImpl;
import com.cloud.mdblog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/friendLink")
public class FriendLinkController {

    @Autowired
    private FriendLinkService friendLinkService;

    @AccessAuth
    @PostMapping("/create")
    public Result create(@RequestBody FriendLink friendLink){
        friendLinkService.create(friendLink);
        return Result.ok(friendLink);
    }

    @AccessAuth
    @PostMapping("/delete")
    public Result delete(Integer id){
        friendLinkService.delete(id);
        return Result.ok();
    }

    @AccessAuth
    @PostMapping("/update")
    public Result update(@RequestBody FriendLink friendLink){

        friendLinkService.update(friendLink);
        return Result.ok(friendLink);
    }

    @PostMapping("/query")
    public Map query(@RequestBody FriendLink friendLink){
        PageInfo<FriendLink> pageInfo = friendLinkService.query(friendLink);
        System.out.println(pageInfo.getList());
        return Result.ok(pageInfo);
    }

    @GetMapping("/getListLink")
    public Result getListLink(){
        FriendLink friendLink = new FriendLink();
        List<FriendLink> friendLink1 = friendLinkService.getFriendLink(friendLink);
        return Result.ok(friendLink1);
    }


    @PostMapping("/detail")
    public Result detail(Integer id){
        FriendLink detail = friendLinkService.detail(id);
        return Result.ok(detail);
    }

    @PostMapping("/count")
    public Result count(@RequestBody FriendLink friendLink){
        int count = friendLinkService.count(friendLink);
        return Result.ok(count);
    }
}

