package com.oxygen.oblog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 后台管理菜单数据封装类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogoInfo {
    private String title;
    private String image;
    private String href;
}
