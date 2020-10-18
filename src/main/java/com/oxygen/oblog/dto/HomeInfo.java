package com.oxygen.oblog.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 后台管理菜单数据封装类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomeInfo {
    private String title;
    private String href;
}
