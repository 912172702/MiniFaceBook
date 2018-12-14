package com.dbc.minifb.business.response;

import com.dbc.minifb.business.constant.Code;

/**
 * @Author: Knox
 * @Date: 2018/12/3 7:04 PM
 * @Description: You Know
 * @Version 1.0
 */
public class ResponseFactory {

    public static BaseResponse response(int state, String msg) {
        return new BaseResponse<>(state, msg);
    }

    public static BaseResponse response(Code code) {
        return new BaseResponse<>(code);
    }

    public static <T> BaseResponse response(Code code, T obj) {
        return new BaseResponse<>(code, obj);
    }

}
