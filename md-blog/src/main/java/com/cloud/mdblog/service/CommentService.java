package com.cloud.mdblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.mdblog.entity.Comment;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface CommentService extends IService<Comment> {
    int create(Comment comment);
    int delete(Integer id);
    int update(Comment comment);
    PageInfo<Comment> query(Comment comment);
    List<Comment> getCommentArticle(Comment comment);
    Comment detail(Integer id);
    int count(Comment comment);
}
