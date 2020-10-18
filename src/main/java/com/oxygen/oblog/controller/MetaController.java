package com.oxygen.oblog.controller;

import com.github.pagehelper.PageInfo;
import com.oxygen.oblog.dto.LayuiPage;
import com.oxygen.oblog.dto.PageRequest;
import com.oxygen.oblog.dto.Response;
import com.oxygen.oblog.entity.Meta;
import com.oxygen.oblog.service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/meta")
public class MetaController {
    @Autowired
    private MetaService metaService;

    @GetMapping(value = "/getAll", params = {"page", "limit"})
    public LayuiPage getAll(int page, int limit) throws Exception {
        PageInfo<Meta> p = metaService.getMetaPage(new PageRequest(page, limit));
        return new LayuiPage(0, "", p.getTotal(), p.getList());
    }
}
