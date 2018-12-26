package com.ycjw.technology.exception;

import com.ycjw.technology.model.Response;

public class ExceptionZyc extends Exception {
    public static final ExceptionZyc PASSWORD_IS_NOT_TRUE = new ExceptionZyc(new Response("密码不符合规范",null));
    public static final ExceptionZyc USERNAME_IS_NOT_TRUE = new ExceptionZyc(new Response("用户名不符合规范",null));
    public static final ExceptionZyc PASSWORD_IS_WRONG = new ExceptionZyc(new Response("密码错误",null));
    public static final ExceptionZyc PASSWORD_IS_NOT_EQUALS = new ExceptionZyc(new Response("两次密码不相同",null));
    public static final ExceptionZyc USER_IS_NOT_EXIST = new ExceptionZyc(new Response("用户不存在",null));
    public static final ExceptionZyc USER_IS_EXIST = new ExceptionZyc(new Response("用户已存在",null));
    private Response response;

    public ExceptionZyc() {
    }

    public ExceptionZyc(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}