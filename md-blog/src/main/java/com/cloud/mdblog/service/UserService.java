package com.cloud.mdblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.mdblog.entity.User;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public interface UserService extends IService<User> {
    int create(User user);
    int delete(Integer id);
    int update(User user);
    PageInfo<User> query(User user);
    User login(String userName,String password);
    User detail(Integer id);
    int count(User user);
    void code(HttpSession sess, HttpServletResponse response) throws IOException;
}
