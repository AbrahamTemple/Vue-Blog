package com.cloud.mdblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.mdblog.entity.Tag;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.8.24
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
public interface TagService extends IService<Tag> {
    int create(Tag tag);
    int delete(Integer id);
    int update(Tag tag);
    PageInfo<Tag> query(Tag tag);
    List<Tag> all();
    Tag detail(Integer id);
    int count(Tag tag);
}
