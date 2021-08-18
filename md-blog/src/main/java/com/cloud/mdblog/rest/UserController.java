package com.cloud.mdblog.rest;


import com.github.pagehelper.PageInfo;
import com.cloud.mdblog.entity.User;
import com.cloud.mdblog.service.impl.UserServiceImpl;
import com.cloud.mdblog.utils.RequestUtils;
import com.cloud.mdblog.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Value("${upload-path.user-img}")
    private String paths;

    @ApiOperation("用户创建")
    @PostMapping("/create")
    public Result  create(@RequestBody User user){
        userServiceImpl.create(user);
        return Result.ok(user);
    }

    @ApiOperation("用户删除")
    @PostMapping("/delete")
    public Result delete(Integer id){
        userServiceImpl.delete(id);
        return Result.ok();
    }

    @ApiOperation("用户更新")
    @PostMapping("/update")
    public Result update(@RequestBody User user){
        userServiceImpl.update(user);
        return Result.ok(user);
    }

    @ApiOperation("账号状态")
    @PostMapping("/query")
    public Map query(@RequestBody User user){
        PageInfo<User> pageInfo = userServiceImpl.query(user);
        System.out.println(pageInfo.getList());
        return Result.ok(pageInfo);
    }

    @PostMapping("/detail")
    public Result detail(Integer id){
        User detail = userServiceImpl.detail(id);
        return Result.ok(detail);
    }

    @ApiOperation("用户数量")
    @PostMapping("/count")
    public Result count(@RequestBody User user){
        int count = userServiceImpl.count(user);
        return Result.ok(count);
    }

    @ApiOperation("用户图片上传")
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

