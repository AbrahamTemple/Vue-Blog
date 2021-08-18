package com.cloud.mdblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.mdblog.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface CommentMapper extends BaseMapper<Comment> {
    int create(Comment comment);

    int delete(Map<String, Object> paraMap);

    int update(Map<String, Object> paraMap);

    List<Comment> query(Map<String, Object> paramMap);

    Comment detail(Map<String, Object> paramMap);

    int count(Map<String, Object> paramMap);
}
