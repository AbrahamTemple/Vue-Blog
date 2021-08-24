package com.cloud.mdblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.mdblog.entity.Channel;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface ChannelService extends IService<Channel> {
    int create(Channel channel);
    int delete(Integer id);
    int update(Channel channel);
    PageInfo<Channel> query(Channel channel);
    PageInfo<Channel> NoByQuery(Channel channel);
    List<Channel> getChannelPos(String  pos);
    List<Channel> all();
    Channel detail(Integer id);
    int count(Channel channel);
}
