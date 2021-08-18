package com.cloud.mdblog.mapper;

import com.cloud.mdblog.entity.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TagMapper {

    int create(Tag tag);

    int delete(Map<String, Object> paraMap);

    int update(Map<String, Object> paraMap);

    List<Tag> query(Map<String, Object> paramMap);

    Tag detail(Map<String, Object> paramMap);

    int count(Map<String, Object> paramMap);
}
