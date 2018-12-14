package com.dbc.minifb.business.response;

import com.dbc.minifb.business.constant.Code;
import lombok.Data;

/**
 * @Author: Knox
 * @Date: 2018/12/3 7:04 PM
 * @Description: You Know
 * @Version 1.0
 */
@Data
public class BaseResponse<T> {

    private int state;
    private String msg;
    private T obj;

    BaseResponse(int state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    BaseResponse(Code code) {
        this.state = code.getState();
        this.msg = code.getMsg();
    }

    BaseResponse(Code code, T obj) {
        this.state = code.getState();
        this.msg = code.getMsg();
        this.obj = obj;
    }
}
