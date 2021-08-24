package com.cloud.mdblog.mvc;


import com.cloud.mdblog.entity.User;
import com.cloud.mdblog.exception.MyException;
import com.cloud.mdblog.jwt.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * token拦截器
 */
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

      String token=request.getHeader(JWTUtils.token);
        try {
            User user = JWTUtils.getUser(token);
            //token续期
            String newToken = JWTUtils.sign(user);
            response.setHeader(JWTUtils.token,newToken);
            request.setAttribute("user",user);
        } catch (MyException e){
            log.info("---没有token,获取用户信息异常---");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
