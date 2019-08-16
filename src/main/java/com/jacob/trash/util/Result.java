package com.jacob.trash.util;

import org.springframework.stereotype.Component;

@Component
public class Result {
    public static final int SUCCESS = 0;//成功
    public static final int ERROR = 1;//失败

    public int type;
    public String message;

    public Result success(String messge){
        this.message = messge;
        this.type = SUCCESS;
        return this;
    }

    public Result error(String messge){
        this.message = messge;
        this.type = ERROR;
        return this;
    }
}
