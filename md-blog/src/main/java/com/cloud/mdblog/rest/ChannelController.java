package com.cloud.mdblog.rest;


import com.github.pagehelper.PageInfo;
import com.cloud.mdblog.entity.Channel;
import com.cloud.mdblog.entity.User;
import com.cloud.mdblog.service.impl.ChannelServiceImpl;
import com.cloud.mdblog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/channel")
public class ChannelController {

    @Autowired
    private ChannelServiceImpl channelServiceImpl;

    @PostMapping("/create")
    public Result create(@RequestBody Channel channel, HttpServletRequest request){
        User user = (User) request.getAttribute("user");
        channel.setCreateUser(user.getId());
        if (channel.getParentId()==null){
            channel.setParentId(0);
        }
        channelServiceImpl.create(channel);
        return Result.ok(channel);
    }

    @PostMapping("/delete")
    public Result delete(Integer id){
        channelServiceImpl.delete(id);
        return Result.ok();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Channel channel){
        channelServiceImpl.update(channel);
        return Result.ok(channel);
    }
    @PostMapping("/query")
    public Map query(@RequestBody Channel channel){
        PageInfo<Channel> pageInfo = channelServiceImpl.query(channel);
        return Result.ok(pageInfo);
    }

    @PostMapping("/tree")
    public Result tree(){
        List<Channel> list = channelServiceImpl.all();
        List<Map<String,Object>> mapList=new ArrayList<>();
        for (Channel channel : list) {
            //首先获取顶级栏目
            if (channel.getParentId()==0){
                Map<String,Object> map =new HashMap<>();
                map.put("id",channel.getId());
                map.put("label",channel.getName());
                List<Map<String,Object>> children=new ArrayList<>();
                for (Channel entity : list) {
                    if (entity.getParentId() == channel.getId() ){
                        Map<String,Object> subMap=new HashMap<>();
                        subMap.put("id",entity.getId());
                        subMap.put("label",entity.getName());
                        children.add(subMap);
                    }
                }
                map.put("children",children);
                mapList.add(map);
            }
        }
        return Result.ok(mapList);
    }

    @PostMapping("/detail")
    public Result detail(Integer id){
        Channel detail = channelServiceImpl.detail(id);
        return Result.ok(detail);
    }
    @PostMapping("/count")
    public Result count(@RequestBody Channel channel){
        int count = channelServiceImpl.count(channel);
        return Result.ok(count);
    }
}

