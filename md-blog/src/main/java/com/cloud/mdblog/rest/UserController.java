package com.cloud.mdblog.rest;


import com.cloud.mdblog.jwt.AccessAuth;
import com.cloud.mdblog.service.UserService;
import com.github.pagehelper.PageInfo;
import com.cloud.mdblog.entity.User;
import com.cloud.mdblog.utils.RequestUtils;
import com.cloud.mdblog.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${upload-path.user-img}")
    private String paths;

    @AccessAuth
    @PostMapping("/create")
    public Result  create(@RequestBody User user){
        userService.create(user);
        return Result.ok(user);
    }

    @AccessAuth
    @PostMapping("/delete")
    public Result delete(Integer id){
        userService.delete(id);
        return Result.ok();
    }

    @AccessAuth
    @PostMapping("/update")
    public Result update(@RequestBody User user){
        userService.update(user);
        return Result.ok(user);
    }


    @PostMapping("/query")
    public Map query(@RequestBody User user){
        PageInfo<User> pageInfo = userService.query(user);
        System.out.println(pageInfo.getList());
        return Result.ok(pageInfo);
    }

    @AccessAuth
    @PostMapping("/detail")
    public Result detail(Integer id){
        User detail = userService.detail(id);
        return Result.ok(detail);
    }

    @PostMapping("/count")
    public Result count(@RequestBody User user){
        int count = userService.count(user);
        return Result.ok(count);
    }

    @AccessAuth
    @PostMapping("/upload")
    public Result upload(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException {
        String originalFilename = file.getOriginalFilename();
        //获取文件名后缀
        String ex = originalFilename.substring(originalFilename.lastIndexOf(".")+1,originalFilename.length());
        String newFileNamePrefix= UUID.randomUUID().toString();
        String newFileName=newFileNamePrefix+"."+ex;
        file.transferTo(new File(paths,newFileName));

        System.out.println(RequestUtils.getBasePath(request)+"upload/"+newFileName);
        //最后返回的是一个可以访问的全路径
        return Result.ok(RequestUtils.getBasePath(request)+"upload/"+newFileName);
    }

}

