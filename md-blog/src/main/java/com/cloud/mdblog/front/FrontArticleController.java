package com.cloud.mdblog.front;

import com.cloud.mdblog.entity.Article;
import com.cloud.mdblog.entity.ArticleTag;
import com.cloud.mdblog.entity.Comment;
import com.cloud.mdblog.entity.Tag;
import com.cloud.mdblog.front.domian.FrontArticleService;
import com.cloud.mdblog.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/front/article")
public class FrontArticleController {

    @Resource
    FrontArticleService frontArticleService;

    @GetMapping("/get")
    public Result getById(Integer id){
        return frontArticleService.getById(id);
    }

    @GetMapping("/getLikeArticle")
    public Result getLikeArticle(Integer id){
        return frontArticleService.getLikeArticle(id);
    }

    @GetMapping("/getByChannelId")
    public Map getByIdChannel(Integer page){
        return frontArticleService.getByIdChannel(page);
    }
    /**
     * 点击排行
     * @return
     */
    @GetMapping("/getOlderByArticle")
    public Result getOlderArticle(){
        return frontArticleService.getOlderArticle();
    }

    @GetMapping("/getNotice")
    public Result getNotice(Integer channelId){
        return frontArticleService.getNotice(channelId);
    }

    /**
     * 分页查询文章
     * @return
     */
    @GetMapping("/page")
    public Map pageArticleList(Integer number){
        return frontArticleService.pageArticleList(number);
    }

    @GetMapping("/getTop")
    public Result getById(Article article,Integer top){
        return frontArticleService.getById(article, top);
    }

    @GetMapping("/Top")
    public Result getById(){
        return frontArticleService.getById();
    }

	/**
     * 插入评论
     */
    @PostMapping("/create")
    public Result create(@RequestBody Comment comment){
        return frontArticleService.create(comment);
    }

    /**
     * 查询文章评论
     * @param articleId
     * @return
     */
    @GetMapping("/getCommentArticleId")
    public Result getCommentArticleId(Integer articleId){
        return frontArticleService.getCommentArticleId(articleId);
    }

    //查询出随机的两条数据

    @GetMapping("/getRandomArticle")
    public Result getRandomArticle(){
        return frontArticleService.getRandomArticle();
    }

    @PostMapping("/search")
    public Map search(@RequestBody Article article){
        return frontArticleService.search(article);
    }

    @PostMapping("/getArticleTag")
    public Map getArticleTag(@RequestBody ArticleTag articleTag){
        return frontArticleService.getArticleTag(articleTag);
    }

    //标签云
    @PostMapping("/all")
    public Result all(Tag tag){
        return frontArticleService.all(tag);
    }

}
