package com.cloud.mdblog.rest;

import com.cloud.mdblog.jwt.AccessAuth;
import com.cloud.mdblog.utils.RequestUtils;
import com.cloud.mdblog.utils.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class UploadController {

    @Value("${upload-path.title-img}")
    private String paths;

    @AccessAuth
    @PostMapping("/uploadFile")
    public Result upload(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException {
        String originalFilename = file.getOriginalFilename();
        //获取文件名后缀
        String ex = originalFilename.substring(originalFilename.lastIndexOf(".")+1,originalFilename.length());
        String newFileNamePrefix= UUID.randomUUID().toString();
        String newFileName=newFileNamePrefix+"."+ex;
        ///usr/LyuBlog/uploadL
        //file.transferTo(new File("D:/upload/cms",newFileName));
        file.transferTo(new File(paths,newFileName));
        //最后返回的是一个可以访问的全路径
        return Result.ok(RequestUtils.getBasePath(request)+"upload/"+newFileName);
    }
}
