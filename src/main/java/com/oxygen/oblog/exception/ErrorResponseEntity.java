package com.oxygen.oblog.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 异常信息模板
 * @author Oxygen
 * @since 2020/09/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseEntity {

    private int code;
    private String message;

}