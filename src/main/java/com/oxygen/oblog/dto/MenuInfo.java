package com.oxygen.oblog.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 后台管理菜单数据封装类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuInfo {
    private String title;
    private String icon;
    private String href;
    private String target;
    private List<MenuInfo> child;

    public MenuInfo(String title, String icon, String href) {
        this.child = null;
        this.target = "_self";
        this.title = title;
        this.icon = icon;
        this.href = href;
    }
}
