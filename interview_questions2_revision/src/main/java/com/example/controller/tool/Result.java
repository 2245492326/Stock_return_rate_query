package com.example.controller.tool;

/**
 * 设置统一数据返回结果类
 *
 * @author Administrator
 */

public class Result {
    //状态码
    private Integer status;

    //返回数据
    private Object data;

    //返回消息
    private String message;

    public Result() {
    }

    public Result(Integer code, Object data) {
        this.status = code;
        this.data = data;
    }

    public Result(Integer status, Object data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
