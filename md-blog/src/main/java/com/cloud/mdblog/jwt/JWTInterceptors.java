package com.cloud.mdblog.jwt;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JWTInterceptors implements HandlerInterceptor {

    /**
     * 拦截处理程序的执行。HandlerMapping确定后调用
     * 横切出程序执行前的http请求，handler即是将要执行的程序
     * @param request 当前http请求
     * @param response 当前http响应
     * @param handler 为类型和/或实例求值选择要执行的处理程序
     * @return 执行链是否应该继续
     * 下一个拦截器或处理程序本身。否则，DispatcherServlet假设
     * 这个拦截器已经处理了响应本身。
     */

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		// 如果不是方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        // 反射得到方法
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 得到方法上的该注解
        AccessAuth methodAnnotation = method.getAnnotation(AccessAuth.class);
        // 有该注解，需要认证
        if (methodAnnotation != null) {
            log.info("---权限需要认证---");
            // 拦截
        	Map<String, Object> map = new HashMap<>();
            //获取请求头中令牌
            String token = request.getHeader("token");
            if(!JWTUtils.verify(token)) {//Jwt验证令牌，认证失败会返回false;
                map.put("msg", "您没有访问权限");
                map.put("code",401);
                map.put("state", false);//设置状态
                String json = new ObjectMapper().writeValueAsString(map);
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().println(json); //把认证失败的对象横切到响应之后返回出去
                return false;
            } log.info("---权限通过---");
        }
        return true;
    }

}
