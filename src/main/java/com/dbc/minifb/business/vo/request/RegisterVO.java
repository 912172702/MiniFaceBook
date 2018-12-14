package com.dbc.minifb.business.vo.request;

import lombok.Data;

/**
 * @Author: Knox
 * @Date: 2018/12/3 8:10 PM
 * @Description: You Know
 * @Version 1.0
 */
@Data
public class RegisterVO {
    private String username;
    private String password;
    private String phoneNumber;
    private Integer age;
    private Integer sex;
}
