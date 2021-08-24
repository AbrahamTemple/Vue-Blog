package com.cloud.mdblog.rest;

import com.cloud.mdblog.entity.User;
import com.cloud.mdblog.jwt.JWTUtils;
import com.cloud.mdblog.service.UserService;
import com.cloud.mdblog.service.impl.UserServiceImpl;
import com.cloud.mdblog.utils.Maps;
import com.cloud.mdblog.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody Map<String,String> paramMap,HttpSession sess){
        String userName = paramMap.get("username");
        String password = paramMap.get("password");
        String code = paramMap.get("code");

        log.info("--用户:"+userName+" --密码"+password+" --验证码:"+code);
        User user = userService.login(userName, password);
//        String checkCode = sess.getAttribute("checkCode").toString();
//        log.info("--session:"+checkCode);

        if (code.length() != 4){
            return Result.fail("验证码填写有误");
        }

        if (user!= null){
            String token = JWTUtils.sign(user);

            if (user.getStatus().equals("F")){
                return Result.fail("您的账号已被禁用");
            }
            if (user.getStatus().equals("D")){
                return Result.fail("您的账号已被设置为删除");
            }
            return Result.ok(Maps.build().put("token",token).put("user",user).getMap());
        } else {
            return Result.fail("用户名或密码错误");
        }

    }


}
