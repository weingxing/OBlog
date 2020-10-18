package com.oxygen.oblog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 后台管理菜单数据封装类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    private HomeInfo homeInfo;
    private LogoInfo logoInfo;
    private List<MenuInfo> menuInfo;
}
