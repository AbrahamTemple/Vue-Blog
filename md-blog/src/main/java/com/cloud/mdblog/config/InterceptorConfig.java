package com.cloud.mdblog.config;

import com.cloud.mdblog.jwt.JWTInterceptors;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.8.23
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    /**
     * 增加一个jwt权限拦截器
     * @param registry 拦截注册
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptors())
                .addPathPatterns("/**")         //其他接口需要token验证
                .excludePathPatterns("/mos/login");
    }

}

