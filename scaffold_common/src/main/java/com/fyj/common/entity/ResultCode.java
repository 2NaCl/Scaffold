package com.fyj.common.entity;

public enum ResultCode {
    SUCCESS(true,10000,"操作成功！"),
    //---系统错误返回码-----
    FAIL(false,10001,"操作失败"),
    UNAUTHENTICATED(false,10002,"您还未登录"),
    UNAUTHORISE(false,10003,"权限不足"),
    SERVER_ERROR(false,99999,"抱歉，系统繁忙，请稍后重试！"),
    ALREADLY(false,10004,"抱歉，此账号已被注册"),

    //---用户操作返回码  2xxxx----
    MOBILEORPASSWORDERROR(false,20001,"用户名或密码错误");

    //---上层权限操作返回码  3xxxx----

    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    ResultCode(boolean success,int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public boolean success() {
        return success;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }
}
