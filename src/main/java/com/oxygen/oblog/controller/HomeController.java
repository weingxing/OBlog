package com.oxygen.oblog.controller;

import com.oxygen.oblog.dto.PageRequest;
import com.oxygen.oblog.dto.Response;
import com.oxygen.oblog.service.CommentService;
import com.oxygen.oblog.service.ContentService;
import com.oxygen.oblog.service.OptionsService;
import com.oxygen.oblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 首页控制类
 * @author Oxygen
 * @since 2020/09/25
 */
@Controller
public class HomeController {
    @Autowired
    private OptionsService optionsService;

    @GetMapping("/")
    public String home(Model model) throws Exception {
        model.addAttribute("title", optionsService.getTitle());
        model.addAttribute("site", optionsService.getSiteUrl());
        return "home";
    }

    @GetMapping("/archives")
    public String archives(Model model) throws Exception {
        model.addAttribute("title", optionsService.getTitle());
        model.addAttribute("site", optionsService.getSiteUrl());
        return "archives";
    }

    @GetMapping("/about")
    public String about(Model model) throws Exception {
        model.addAttribute("title", optionsService.getTitle());
        model.addAttribute("site", optionsService.getSiteUrl());
        return "about";
    }

    @GetMapping("/category")
    public String category(Model model) throws Exception {
        model.addAttribute("title", optionsService.getTitle());
        model.addAttribute("site", optionsService.getSiteUrl());
        return "category";
    }

    @GetMapping("/getMusicUrl")
    @ResponseBody
    @CrossOrigin
    public String getMusic() throws Exception {
        return optionsService.getMusicUrl();
    }

}
