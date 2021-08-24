package com.cloud.mdblog.front;

import com.cloud.mdblog.front.domian.FrontChannelService;
import com.cloud.mdblog.entity.Article;
import com.cloud.mdblog.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/front/channel")
public class FrontChannelController {

    @Resource
    FrontChannelService frontChannelService;

    @GetMapping("/get")
    public Result getById(Integer id){
        return frontChannelService.getById(id);
    }

    @PostMapping("/getChannelArticle")
    public Map getChannelArticle(@RequestBody Article article){
        return frontChannelService.getChannelArticle(article);
    }

    @GetMapping("/noByChannel")
    public Map getChannelArticle(Integer channelId){
        return frontChannelService.getChannelArticle(channelId);
    }

    @GetMapping("/getChannelArticlePos")
    public Result getChannelArticlePos(Integer channelId){
        return frontChannelService.getChannelArticlePos(channelId);
    }

    @GetMapping("/queryByPos")
    public Result getChannelByPos(String pos){
        return frontChannelService.getChannelByPos(pos);
    }
}
