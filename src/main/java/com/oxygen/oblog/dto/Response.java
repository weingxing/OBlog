package com.oxygen.oblog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * 响应信息封装类
 */
@Data
@AllArgsConstructor
public class Response {
    private int code;
    private String msg;
    private Object content;
}
