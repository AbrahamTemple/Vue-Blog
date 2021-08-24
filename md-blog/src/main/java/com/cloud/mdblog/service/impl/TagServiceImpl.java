package com.cloud.mdblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.mdblog.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.cloud.mdblog.entity.Tag;
import com.cloud.mdblog.mapper.TagMapper;
import com.cloud.mdblog.utils.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public int create(Tag tag){
        return tagMapper.create(tag);
    }

    @Override
    public int delete(Integer id){
        return tagMapper.delete(Maps.build(id).getMap());
    }

    @Override
    public int update(Tag tag){
        return tagMapper.update(Maps.build(tag.getId()).beanToMapForUpdate(tag));
    }

    @Override
    public PageInfo<Tag> query(Tag tag){
        if (tag!=null && tag.getPage() != null){
            PageHelper.startPage(tag.getPage(),tag.getLimit());
        }
        List<Tag> list = tagMapper.query(Maps.build().beanToMap(tag));
        return new PageInfo<Tag> (list);
    }

    @Override
    public List<Tag> all(){
        List<Tag> list = tagMapper.query(null);
        return list;
    }

    @Override
    public Tag detail(Integer id){
        return tagMapper.detail(Maps.build(id).getMap());
    }

    @Override
    public int count(Tag tag){
        return tagMapper.count(Maps.build().beanToMap(tag));
    }

}
