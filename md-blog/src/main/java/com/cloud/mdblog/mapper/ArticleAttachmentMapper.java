package com.cloud.mdblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.mdblog.entity.ArticleAttachment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface ArticleAttachmentMapper extends BaseMapper<ArticleAttachment> {
    int create(ArticleAttachment articleAttachment);

    int delete(Map<String, Object> paraMap);

    int update(Map<String, Object> paraMap);

    List<ArticleAttachment> query(Map<String, Object> paramMap);

    ArticleAttachment detail(Map<String, Object> paramMap);

    int count(Map<String, Object> paramMap);

}
