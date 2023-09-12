package com.example.controller.tool;

/*
 * 查询-->get
 * 新增-->post
 * 修改-->put
 * 删除-->delete
 */
public class Code {
    //操作成功
    public static final Integer POST_OK = 20011;
    public static final Integer DELETE_OK = 20021;
    public static final Integer PUT_OK = 20031;
    public static final Integer GET_OK = 20041;

    //操作失败
    public static final Integer POST_ERR = 20010;
    public static final Integer DELETE_ERR = 20020;
    public static final Integer PUT_ERR = 20030;
    public static final Integer GET_ERR = 20040;

    //异常
    //系统异常
    public static final Integer SYSTEM_ERR = 50010;
    //系统超时异常
    public static final Integer SYSTEM_TIMEOUT_ERR = 50020;
    //业务异常
    public static final Integer BUSINESS_ERR = 50030;
    //未知异常
    public static final Integer SYSTEM_UNKNOW_ERR = 59999;
}
