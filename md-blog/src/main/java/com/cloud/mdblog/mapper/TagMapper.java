package com.cloud.mdblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.mdblog.entity.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TagMapper extends BaseMapper<Tag> {

    int create(Tag tag);

    int delete(Map<String, Object> paraMap);

    int update(Map<String, Object> paraMap);

    List<Tag> query(Map<String, Object> paramMap);

    Tag detail(Map<String, Object> paramMap);

    int count(Map<String, Object> paramMap);
}
