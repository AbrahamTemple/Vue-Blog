package com.cloud.mdblog.front.domian;

import com.cloud.mdblog.entity.Article;
import com.cloud.mdblog.entity.Channel;
import com.cloud.mdblog.service.ChannelService;
import com.cloud.mdblog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
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
public abstract class FrontChannel implements Front{

    @Autowired
    private ChannelService channelService;

    public abstract Map getChannelArticle(Article article);

    public abstract Result getChannelArticlePos(Integer channelId);

    @Override
    public Result getById(Integer id) {
        if (id==null){
            return Result.fail();
        }
        Channel detail = channelService.detail(id);
        return Result.ok(detail);
    }

    public Map getChannelArticle(Integer channelId){
        Channel channel = new Channel();
        channel.setParentId(channelId);
        return Result.ok(channelService.NoByQuery(channel));
    }

    public Result getChannelByPos(String pos){
        if (StringUtils.isEmpty(pos)){
            return Result.fail();
        }
        List<Channel> channelPos = channelService.getChannelPos(pos.toUpperCase());
        List<Map<String,Object>> mapList=new ArrayList<>();
        for (Channel channel : channelPos) {
            //首先获取顶级栏目
            if (channel.getParentId()==0){
                Map<String,Object> map =new HashMap<>();
                map.put("id",channel.getId());
                map.put("name",channel.getName());
                if (channel!= null &&"Y".equals(channel.getSingle())){
                    map.put("single",true);
                }
                List<Map<String,Object>> children=new ArrayList<>();
                for (Channel entity : channelPos) {
                    if (entity.getParentId() == channel.getId() ){
                        Map<String,Object> subMap=new HashMap<>();
                        subMap.put("id",entity.getId());
                        subMap.put("name",entity.getName());
                        if (entity!= null &&"Y".equals(entity.getSingle())){
                            map.put("single",true);
                        }
                        children.add(subMap);
                    }
                }
                if (children.size()>0){
                    map.put("children",children);
                }
                mapList.add(map);
            }
        }
        return Result.ok(mapList);
    }
}
