package com.cloud.mdblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cloud.mdblog.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhaoguoshun
 * @since 2020-12-11
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    int create(User user);

    int delete(Map<String, Object> paraMap);

    int update(Map<String, Object> paraMap);

    List<User> query(Map<String, Object> paramMap);

    User detail(Map<String, Object> paramMap);

    int count(Map<String, Object> paramMap);


}
