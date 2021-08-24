package com.cloud.mdblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.cloud.mdblog.entity.User;
import com.cloud.mdblog.mapper.UserMapper;
import com.cloud.mdblog.service.UserService;
import com.cloud.mdblog.utils.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int create(User user){
        return userMapper.create(user);
    }

    @Override
    public int delete(Integer id){
        return userMapper.delete(Maps.build(id).getMap());
    }

    @Override
    public int update(User user){
        return userMapper.update(Maps.build(user.getId()).beanToMapForUpdate(user));
    }

    @Override
    public PageInfo<User> query(User user){
        if (user!=null && user.getPage() != null){
            PageHelper.startPage(user.getPage(),user.getLimit());
        }
        List<User> list = userMapper.query(Maps.build().beanToMap(user));
        for (User value : list) {
            String status = value.getStatus();
            if (status.equals("T")) {
                value.setStatus("正常");
            }
            if (status.equals("F")) {
                value.setStatus("禁用");
            }
            if (status.equals("D")) {
                value.setStatus("待删除");
            }
        }
        return new PageInfo<> (list);
    }

    @Override
    public User login(String userName,String password){
        return userMapper.detail(Maps.build()
                .put("userName",userName)
                .put("password",password)
                .getMap());
    }

    @Override
    public User detail(Integer id){
        return userMapper.detail(Maps.build(id).getMap());
    }

    @Override
    public int count(User user){
        return userMapper.count(Maps.build().beanToMap(user));
    }

    @Override
    public void code(HttpSession sess, HttpServletResponse response) throws IOException {
        //浏览器不要缓存
        response.setHeader("pragma","no-cache");
        response.setHeader("cache-control","no-cache");
        response.setHeader("expires","0");

        //在内存中创建一个长80，宽30的图片，默认黑色背景
        int width = 80;
        int height = 30;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

        //获取画笔
        Graphics g = image.getGraphics();
        //设置画笔颜色为紫色
        g.setColor(Color.MAGENTA);
        //填充图片
        g.fillRect(0,0, width,height);

        //产生4个随机验证码
        String checkCode = getCheckCode();
        //将验证码放入HttpSession中
        log.info("当前验证码为："+checkCode);
        sess.setAttribute("checkCode", checkCode);
        //设置字体颜色为蓝色
        g.setColor(Color.CYAN);
        //设置字体的小大
        g.setFont(new Font("黑体",Font.BOLD,24));
        //向图片上写入验证码
        g.drawString(checkCode,15,25);

        //将内存中的图片输出到浏览器
        //参数一：图片对象
        //参数二：图片的格式，如PNG,JPG,GIF
        //参数三：图片输出到哪里去
        ImageIO.write(image,"PNG",response.getOutputStream());
    }

    /**
     * 产生4位随机字符串
     */
    private String getCheckCode() {
        String base = "0123456789ABCDEFGHIJKLNMOPQRSTUVWXYZabcdefghijklnmopqrstuvwxyz";
        int size = base.length();
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i=1;i<=4;i++){
            //产生0到size-1的随机值
            int index = r.nextInt(size);
            //在base字符串中获取下标为index的字符
            char c = base.charAt(index);
            //将c放入到StringBuffer中去
            sb.append(c);
        }
        return sb.toString();
    }
}
