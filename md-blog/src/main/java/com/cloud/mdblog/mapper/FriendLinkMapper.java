package com.cloud.mdblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.mdblog.entity.FriendLink;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FriendLinkMapper extends BaseMapper<FriendLink> {
    int create(FriendLink friendLink);

    int delete(Map<String, Object> paraMap);

    int update(Map<String, Object> paraMap);

    List<FriendLink> query(Map<String, Object> paramMap);

    FriendLink detail(Map<String, Object> paramMap);

    int count(Map<String, Object> paramMap);
}
