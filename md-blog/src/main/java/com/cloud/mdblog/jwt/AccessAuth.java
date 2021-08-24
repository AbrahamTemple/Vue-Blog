package com.cloud.mdblog.jwt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @version 6.1.8
 * @author: Abraham Vong
 * @date: 2021.8.23
 * @GitHub https://github.com/AbrahamTemple/
 * @description:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessAuth {
}
