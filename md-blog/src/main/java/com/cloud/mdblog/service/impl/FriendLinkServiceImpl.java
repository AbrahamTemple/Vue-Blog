package com.cloud.mdblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.cloud.mdblog.entity.FriendLink;
import com.cloud.mdblog.mapper.FriendLinkMapper;
import com.cloud.mdblog.service.FriendLinkService;
import com.cloud.mdblog.utils.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FriendLinkServiceImpl extends ServiceImpl<FriendLinkMapper, FriendLink> implements FriendLinkService {

    @Autowired
    private FriendLinkMapper friendLinkMapper;


    public int create(FriendLink friendLink){
        return friendLinkMapper.create(friendLink);
    }

    public int delete(Integer id){
        return friendLinkMapper.delete(Maps.build(id).getMap());
    }

    public int update(FriendLink friendLink){
        System.out.println(friendLink);

        System.out.println(Maps.build().beanToMap(friendLink));
        return friendLinkMapper.update(Maps.build(friendLink.getId()).beanToMapForUpdate(friendLink));
    }

    public PageInfo<FriendLink> query(FriendLink friendLink){
        if (friendLink!=null && friendLink.getPage() != null){
            PageHelper.startPage(friendLink.getPage(),friendLink.getLimit());
        }
        List<FriendLink> list = friendLinkMapper.query(Maps.build().beanToMap(friendLink));
        return new PageInfo<FriendLink> (list);
    }


    public List<FriendLink> getFriendLink(FriendLink friendLink){
        List<FriendLink> list = friendLinkMapper.query(Maps.build().beanToMap(friendLink));
        return  list;
    }


    public FriendLink detail(Integer id){
        return friendLinkMapper.detail(Maps.build(id).getMap());
    }

    public int count(FriendLink friendLink){
        return friendLinkMapper.count(Maps.build().beanToMap(friendLink));
    }

}
