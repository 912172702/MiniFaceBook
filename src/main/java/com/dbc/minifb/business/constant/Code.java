package com.dbc.minifb.business.constant;

/**
 * @Author: Knox
 * @Date: 2018/12/3 7:05 PM
 * @Description: You Know
 * @Version 1.0
 */
public enum Code {

    SUCCESS(0, "成功"),
    USERNAME_OR_PSW_ERROR(1, "用户名或密码错误"),
    USER_INFO_NOT_COMPLETE(2, "用户信息不完整"),
    USERNAME_ALREADY_EXISTS(3,"用户名已存在"),
    TYPE_CONVERT_ERROR(4,"类型转换错误");
    private int state;
    private String msg;

    Code() {
    }

    Code(int state, String msg) {
        this.state = state;
        this.msg = msg;
    }


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
