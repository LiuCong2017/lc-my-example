package com.lc.annotation.bean;

/**
 * @author : [Administrator]
 * @version : [v1.0]
 * @description : []
 * @createTime : [2021/11/23 09:21]
 */
public enum ResponseCode {
    SUCCESS(200,"请求成功"),
    ERROR(500,"请求失败");

    private Integer code;
    private String message;

    private ResponseCode(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    public Integer code(){
        return this.code;
    }

    public String message() {
        return this.message;
    }
}
