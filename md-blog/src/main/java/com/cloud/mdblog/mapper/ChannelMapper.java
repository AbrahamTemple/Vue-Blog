package com.cloud.mdblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.mdblog.entity.Channel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ChannelMapper extends BaseMapper<Channel> {
    int create(Channel channel);

    int delete(Map<String, Object> paraMap);

    int update(Map<String, Object> paraMap);

    List<Channel> query(Map<String, Object> paramMap);

    Channel detail(Map<String, Object> paramMap);

    int count(Map<String, Object> paramMap);
}
