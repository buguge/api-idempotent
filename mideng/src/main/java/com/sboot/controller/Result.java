package com.sboot.controller;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Result {
    int code;
    String msg;

    public Result(int code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public static Result ok(String msg){
        return new Result(200,msg);
    }

    public static Result error(String msg){
        return new Result(500,msg);
    }

}
