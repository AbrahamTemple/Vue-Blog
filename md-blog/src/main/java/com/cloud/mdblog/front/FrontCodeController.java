package com.cloud.mdblog.front;

import com.cloud.mdblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.8.24
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
@RestController
@RequestMapping("/front/check")
public class FrontCodeController {

    @Autowired
    private UserService userService;

    @RequestMapping("/code")
    public void checkCode(HttpSession session, HttpServletResponse response) throws IOException {
        userService.code(session,response);
    }
}
