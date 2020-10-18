package com.oxygen.oblog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 用户信息封装类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private int uid;
    private String userName;
    private String password;
    private String nick;
    private String email;
    private String right;

}