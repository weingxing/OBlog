package com.oxygen.oblog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页请求封装类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequest {
    //  当前页码
    private int pageNum;
    //  每页数量
    private int pageSize;
}
