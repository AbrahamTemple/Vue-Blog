package com.cloud.mdblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.mdblog.entity.Channel;
import com.cloud.mdblog.entity.User;
import com.cloud.mdblog.mapper.ChannelMapper;
import com.cloud.mdblog.service.ChannelService;
import com.cloud.mdblog.utils.Maps;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ChannelServiceImpl extends ServiceImpl<ChannelMapper, Channel> implements ChannelService {

    @Autowired
    private ChannelMapper channelMapper;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public int create(Channel channel){
        return channelMapper.create(channel);
    }

    @Override
    public int delete(Integer id){
        return channelMapper.delete(Maps.build(id).getMap());
    }

    @Override
    public int update(Channel channel){ return channelMapper.update(Maps.build(channel.getId()).beanToMapForUpdate(channel)); }

    @Override
    public PageInfo<Channel> query(Channel channel){
        if (channel!=null && channel.getPage() != null){
            PageHelper.startPage(channel.getPage(),channel.getLimit());
        }
        List<Channel> list = channelMapper.query(Maps.build().beanToMap(channel));
        for (int i=0; i<list.size();i++){
            if (list.get(i).getCreateUser()!=null){
                User user = userService.detail(list.get(i).getCreateUser());
                if(user!=null){
                    list.get(i).setUserName(user.getNickName());
                }
            }
            String single = list.get(i).getSingle();
            String pos = list.get(i).getPos();
            if (single!=null){
                if (single.equals("Y")){
                    list.get(i).setSingle("单页");
                }
                if (single.equals("N")){
                    list.get(i).setSingle("不单页");
                }
            }
            if (pos!=null){
                if (pos.equals("A")){
                    list.get(i).setPos("导航");
                }
                if (pos.equals("B")){
                    list.get(i).setPos("中间表格");
                }
                if (pos.equals("C")){
                    list.get(i).setPos("公告");
                }
            }
        }
        return new PageInfo<> (list);
    }

    @Override
    public PageInfo<Channel> NoByQuery(Channel channel){
        if (channel!=null && channel.getPage() != null){
            PageHelper.startPage(channel.getPage(),4);
        }
        List<Channel> list = channelMapper.query(Maps.build().beanToMap(channel));
        for (int i=0; i<list.size();i++){
            if (list.get(i).getCreateUser()!=null){
                User user = userService.detail(list.get(i).getCreateUser());
                if (user!=null){
                    list.get(i).setUserName(user.getNickName());
                }
            }
        }
        return new PageInfo<> (list);
    }

    @Override
    public List<Channel> getChannelPos(String  pos){
        return channelMapper.query(Maps.build().put("pos", pos).getMap());
    }

    @Override
    public List<Channel> all(){
        return channelMapper.query(Maps.build().getMap());
    }

    @Override
    public Channel detail(Integer id){
        return channelMapper.detail(Maps.build(id).getMap());
    }

    @Override
    public int count(Channel channel){
        return channelMapper.count(Maps.build().beanToMap(channel));
    }
}
