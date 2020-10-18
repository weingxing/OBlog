package com.oxygen.oblog.controller;

import com.oxygen.oblog.dto.Response;
import com.oxygen.oblog.service.NotifyService;
import com.oxygen.oblog.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 配置信息控制类
 * @author Oxygen
 * @since 2020/09/25
 */
@RestController
@RequestMapping(value = "options")
public class OptionsController {
    @Autowired
    private OptionsService optionsService;
    @Autowired
    private NotifyService notifyService;

    @GetMapping("/getAllOptions")
    public Response getAllOptions() throws Exception {
        return new Response(200,"OK", optionsService.getAll());
    }

    @GetMapping("/getTitle")
    public Response getTitle() throws Exception {
        return new Response(200, "OK", optionsService.getTitle());
    }

    @GetMapping("/getSite")
    public Response getSite() throws Exception {
        return new Response(200, "OK", optionsService.getSiteUrl());
    }

    @GetMapping("/getDescription")
    public Response getDescription() throws Exception {
        return new Response(200, "OK", optionsService.getDescription());
    }

    @GetMapping("/getKeywords")
    public Response getKeywords() throws Exception {
        return new Response(200, "OK", optionsService.getKeywords());
    }

    @GetMapping("/getLatex")
    public Response getLatex() throws Exception {
        return new Response(200, "OK", optionsService.getLatex());
    }

    @GetMapping("/getMusic")
    public Response getMusic() throws Exception {
        return new Response(200, "OK", optionsService.getMusic());
    }

    @GetMapping("/getNotify")
    public Response getNotify() throws Exception {
        return new Response(200, "OK", optionsService.getNotify());
    }

    @GetMapping(value = "/setTitle", params = {"title"})
    public Response setTitle(String title) throws Exception {
        return new Response(200, "OK", optionsService.setTitle(title));
    }

    @GetMapping(value = "/setSite", params = {"site"})
    public Response setSite(String site) throws Exception {
        return new Response(200, "OK", optionsService.setSiteUrl(site));
    }

    @GetMapping(value = "/setDescription", params = {"description"})
    public Response setDescription(String description) throws Exception {
        return new Response(200, "Ok", optionsService.setDescription(description));
    }

    @GetMapping(value = "/setKeywords", params = {"keywords"})
    public Response setKeywords(String keywords) throws Exception {
        return new Response(200, "Ok", optionsService.setKeywords(keywords));
    }

    @GetMapping(value = "/setLatex", params = {"latex"})
    public Response setLatex(String latex) throws Exception {
        return new Response(200, "OK", optionsService.setLatex(latex));
    }

    @GetMapping(value = "/setMusic", params = {"music"})
    public Response setMusic(String music) throws Exception {
        return new Response(200, "OK", optionsService.setMusic(music));
    }

    @GetMapping(value = "/setNotify", params = {"notify"})
    public Response setNotify(String notify) throws Exception {
        return new Response(200, "OK", optionsService.setNotify(notify));
    }

    @PostMapping("/updateAllOptions")
    public Response updateAllOptions(@RequestParam Map<String, String> map) throws Exception {
        List<Boolean> result = new ArrayList<>();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            if ("notify".equals(entry.getKey()) && notifyService.getInfo().getSmtpAddress()==null)
                result.add(false);

            result.add(optionsService.setOption(entry.getKey(), entry.getValue()
                    .replaceAll(" ", "")));
        }

        for(boolean r : result) {
            if (!r)
                return new Response(200, "OK", false);
        }

        return new Response(200,"OK", true);
    }
}
