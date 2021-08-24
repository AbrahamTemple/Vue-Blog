package com.cloud.mdblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.mdblog.entity.FriendLink;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface FriendLinkService extends IService<FriendLink> {
    int create(FriendLink friendLink);
    int delete(Integer id);
    int update(FriendLink friendLink);
    PageInfo<FriendLink> query(FriendLink friendLink);
    List<FriendLink> getFriendLink(FriendLink friendLink);
    FriendLink detail(Integer id);
    int count(FriendLink friendLink);
}
