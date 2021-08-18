package com.cloud.mdblog.rest;

import com.cloud.mdblog.entity.User;
import com.cloud.mdblog.jwt.JWTUtils;
import com.cloud.mdblog.service.impl.UserServiceImpl;
import com.cloud.mdblog.utils.Maps;
import com.cloud.mdblog.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api("登录")
@RestController
public class LoginController {

    @Autowired
    private UserServiceImpl userService;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Result login(@RequestBody @ApiParam("账号密码集合")Map<String,String> paramMap){
        String userName=paramMap.get("userName");
        String password=paramMap.get("password");

        System.out.println(userName+":"+password);
        User user = userService.login(userName, password);

        System.out.println(user);

        if (user!= null){
            String token = JWTUtils.sign(user);

            if (user.getStatus().equals("F")){
                return Result.fail("您的账号已被禁用");
            }
            if (user.getStatus().equals("D")){
                return Result.fail("您的账号已被设置为删除");
            }
            return Result.ok(Maps.build().put("token",token).put("user",user).getMap());
        }else{
            return Result.fail("用户名或密码错误");
        }

    }


}
