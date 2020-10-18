package com.oxygen.oblog.controller;

import com.oxygen.oblog.dto.*;
import com.oxygen.oblog.entity.Right;
import com.oxygen.oblog.service.OptionsService;
import com.oxygen.oblog.service.RightService;
import com.oxygen.oblog.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录控制类
 * @aouth Oxygen
 * @since 2020/09/27
 */
@Controller
public class LoginController {
    @Autowired
    private OptionsService optionsService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private RightService rightService;

    private String right = null;

    @GetMapping("/login")
    public Object login(Model model) throws Exception {
        model.addAttribute("title", optionsService.getTitle()+"-登录");
        model.addAttribute("site", optionsService.getSiteUrl());
        return "login";
    }

    @PostMapping(value = "/login", params = {"status"})
    @ResponseBody
    public Response loginResult(String status) throws Exception {
        if (status.equals("successful")) {
            UserInfo user = securityService.getUserInfo();
            Map<String, Object> map = new HashMap<>();
            map.put("user", user);
            this.right = user.getRight();
//            System.out.println(map);
            return new Response(200, "登录成功", map);
        } else {
            return new Response(200, "登录失败", null);
        }
    }

//    @GetMapping(value = "/url")
//    @ResponseBody
//    public String url() throws Exception {
//        return optionsService.getSiteUrl();
//    }

    @GetMapping("/getMenu")
    @ResponseBody
    public Menu getMenu(Model model) throws Exception {
//        List<Right> rights = rightService.getAllRight();
//        rights.forEach(r -> {
//
//        });
        List<MenuInfo> menuInfos = new ArrayList<>();
        String index = null;
        if ("default".equals(this.right)) {
            index = "page/user/welcome.html";
            menuInfos.add(new MenuInfo("我的文章", "fa fa-book", "page/user/content.html"));
            menuInfos.add(new MenuInfo("我的评论", "fa fa-comment", "page/user/comment.html"));
        }
        else {
            menuInfos.add(new MenuInfo("文章管理", "fa fa-book", "page/content.html"));
            menuInfos.add(new MenuInfo("文章审核", "fa fa-paper-plane", "page/review_content.html"));
            menuInfos.add(new MenuInfo("评论管理", "fa fa-comment", "page/comment.html"));
            menuInfos.add(new MenuInfo("评论审核", "fa fa-comments", "page/review_comment.html"));
            menuInfos.add(new MenuInfo("用户管理", "fa fa-users", "page/user.html"));
            menuInfos.add(new MenuInfo("SMTP设置", "fa fa-bell", "page/smtp.html"));
            menuInfos.add(new MenuInfo("博客设置", "fa fa-cogs", "page/setting.html"));
            index = "page/welcome.html";
        }

        HomeInfo homeInfo = new HomeInfo(optionsService.getTitle(), index);
        LogoInfo logoInfo = new LogoInfo(optionsService.getTitle(),
                "images/logo.png",
                optionsService.getSiteUrl());
        return new Menu(homeInfo, logoInfo, menuInfos);
    }

    @GetMapping("/register")
    public String register(Model model) throws Exception {
        model.addAttribute("title", optionsService.getTitle()+"-注册");
        model.addAttribute("site", optionsService.getSiteUrl());
        return "/register";
    }
}
