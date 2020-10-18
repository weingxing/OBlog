package com.oxygen.oblog.controller;

import com.oxygen.oblog.dto.Response;
import com.oxygen.oblog.entity.Notify;
import com.oxygen.oblog.service.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/smtp")
public class NotifyController {
    @Autowired
    private NotifyService notifyService;

    @GetMapping("/getInfo")
    public Response getInfo() throws Exception {
        return new Response(200, "OK", notifyService.getInfo());
    }

    @PostMapping("/updateInfo")
    public Response updateInfo(@RequestBody Notify notify) throws Exception {
        return new Response(200, "OK", notifyService.updateInfo(notify));
    }
}
