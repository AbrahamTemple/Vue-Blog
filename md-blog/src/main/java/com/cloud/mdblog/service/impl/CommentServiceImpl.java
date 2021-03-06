package com.cloud.mdblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.cloud.mdblog.entity.Comment;
import com.cloud.mdblog.mapper.CommentMapper;
import com.cloud.mdblog.service.CommentService;
import com.cloud.mdblog.utils.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public int create(Comment comment){
        comment.setStatus(1);
        return commentMapper.create(comment);
    }

    @Override
    public int delete(Integer id){
        return commentMapper.delete(Maps.build(id).getMap());
    }

    @Override
    public int update(Comment comment){
        return commentMapper.update(Maps.build(comment.getId()).beanToMapForUpdate(comment));
    }

    @Override
    public PageInfo<Comment> query(Comment comment){
        if (comment!=null && comment.getPage() != null){
            PageHelper.startPage(comment.getPage(),comment.getLimit());
        }
        List<Comment> list = commentMapper.query(Maps.build().beanToMap(comment));

        for (int i=0;i<list.size();i++){
            if (list.get(i).getStatus()==1){
                list.get(i).setStatusName("待审核");
            }else {
                list.get(i).setStatusName("审核通过");
            }
        }
        return new PageInfo<> (list);
    }

    @Override
    public List<Comment> getCommentArticle(Comment comment){
        List<Comment> list = commentMapper.query(Maps.build().beanToMap(comment));
        return list;
    }

    @Override
    public Comment detail(Integer id){
        return commentMapper.detail(Maps.build(id).getMap());
    }

    @Override
    public int count(Comment comment){
        return commentMapper.count(Maps.build().beanToMap(comment));
    }
}
