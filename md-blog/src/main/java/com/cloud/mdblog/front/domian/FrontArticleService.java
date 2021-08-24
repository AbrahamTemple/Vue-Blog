package com.cloud.mdblog.front.domian;

import com.cloud.mdblog.entity.ArticleTag;
import com.cloud.mdblog.entity.Comment;
import com.cloud.mdblog.entity.Tag;
import com.cloud.mdblog.service.ArticleTagService;
import com.cloud.mdblog.service.CommentService;
import com.cloud.mdblog.service.TagService;
import com.cloud.mdblog.utils.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.8.24
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
@Service
public class FrontArticleService extends FrontArticle{

    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private TagService tagService;


    @Override
    public Result create(Comment comment) {
        commentService.create(comment);
        return Result.ok("评论成功,待管理员审核",comment);
    }

    @Override
    public Result getCommentArticleId(Integer articleId) {
        Comment comment = new Comment();
        comment.setArticleId(articleId);
        comment.setStatus(0);
        return Result.ok( commentService.getCommentArticle(comment));
    }

    @Override
    public Map getArticleTag(ArticleTag articleTag) {
        PageInfo<ArticleTag> pageInfo = articleTagService.getArticleId(articleTag);
        return Result.ok(pageInfo);
    }

    @Override
    public Result all(Tag tag) {
        return Result.ok(tagService.all()) ;
    }
}
