package com.example;

/**
 * Created by 小黄 on 2017/10/16.
 */
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
public @interface WXAppId {
    String value();
}
