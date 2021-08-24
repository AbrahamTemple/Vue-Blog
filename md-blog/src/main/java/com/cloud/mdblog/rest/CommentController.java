package com.cloud.mdblog.rest;


import com.cloud.mdblog.jwt.AccessAuth;
import com.cloud.mdblog.service.CommentService;
import com.github.pagehelper.PageInfo;
import com.cloud.mdblog.entity.Comment;
import com.cloud.mdblog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @AccessAuth
    @PostMapping("/delete")
    public Result delete(Integer id){
        commentService.delete(id);
        return Result.ok();
    }

    @AccessAuth
    @PostMapping("/update")
    public Result update(@RequestBody Comment comment){
        commentService.update(comment);
        return Result.ok(comment);
    }
    @PostMapping("/query")
    public Map query(@RequestBody Comment comment){
        PageInfo<Comment> pageInfo = commentService.query(comment);
        System.out.println(pageInfo.getList());
        return Result.ok(pageInfo);
    }


    @PostMapping("/detail")
    public Result detail(Integer id){
        Comment detail = commentService.detail(id);
        return Result.ok(detail);
    }

    @PostMapping("/count")
    public Result count(@RequestBody Comment comment){
        int count = commentService.count(comment);
        return Result.ok(count);
    }

    /**
     * 查询出所有状态为1的评论
     * @return
     */
    @PostMapping("/getStatusComment")
    public Map getStatusComment(Comment comment){
        System.out.println("评论的内容"+comment);
        comment.setStatus(1);
        PageInfo<Comment> pageInfo = commentService.query(comment);
        System.out.println(pageInfo.getList());
        return Result.ok(pageInfo);
    }

    //分页查询出所有状态为1的评论
    @GetMapping("/getPageCommentId")
    public Map getStatusComment(Integer pageId){
        Comment comment = new Comment();
        System.out.println("评论的内容"+pageId);
        comment.setStatus(1);
        comment.setPage(pageId);
        PageInfo<Comment> pageInfo = commentService.query(comment);
        System.out.println(pageInfo.getList());
        return Result.ok(pageInfo);
    }

    /**
     * 审核通过
     */
    @AccessAuth
    @GetMapping("/getUpdateStatus")
    public Result getUpdateStatus(Integer commentId){
        Comment comment = new Comment();
        comment.setId(commentId);
        comment.setStatus(0);
        commentService.update(comment);
        return Result.ok();
    }

}

