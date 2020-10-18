package com.oxygen.oblog.controller;

import com.github.pagehelper.PageInfo;
import com.oxygen.oblog.dto.ContentInfo;
import com.oxygen.oblog.dto.LayuiPage;
import com.oxygen.oblog.dto.PageRequest;
import com.oxygen.oblog.dto.Response;
import com.oxygen.oblog.entity.Content;
import com.oxygen.oblog.service.ContentService;
import com.oxygen.oblog.service.NotifyService;
import com.oxygen.oblog.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 文章控制类
 * @author Oxygen
 * @since 2020/09/25
 */
@RestController
@RequestMapping("/content")
public class ContentController {
    @Autowired
    private ContentService contentService;

    @Autowired
    private NotifyService notifyService;

    @Autowired
    private OptionsService optionsService;


    @GetMapping(value = "/getPublicByPage", params = {"page", "limit"})
    public LayuiPage getPublicByPage(int page, int limit) throws Exception {
        PageRequest request = new PageRequest(page, limit);
        PageInfo<ContentInfo> p = contentService.getPublicContentByPage(request);
        return new LayuiPage(0, "", p.getTotal(), p.getList());
    }

    @GetMapping(value = "/getReviewedByPage", params = {"page", "limit"})
    public LayuiPage getReviewedByPage(int page, int limit) throws Exception {
        PageRequest request = new PageRequest(page, limit);
        PageInfo<ContentInfo> p = contentService.getReviewedContentByPage(request);
        return new LayuiPage(0, "", p.getTotal(), p.getList());
    }

    @GetMapping(value = "/getMyContentByPage", params = {"uid", "page", "limit"})
    public LayuiPage getMyContentByPage(int uid, int page, int limit) throws Exception {
        PageRequest request = new PageRequest(page, limit);
        PageInfo<ContentInfo> p = contentService.getMyContentByPage(uid, request);
        return new LayuiPage(0, "", p.getTotal(), p.getList());
    }

    @PostMapping(value = "/addContent")
    public Response addContent(@RequestBody ContentInfo contentInfo) throws Exception {
        Content record = contentService.contentInfo2Content(contentInfo);
        if("1".equals(optionsService.getNotify()) && record.getAuthorId() != 1) {
            record.setStatus("reviewed");
            notifyService.sendNotify("有新的文章待审核",
                    "有来自" + contentInfo.getAuthor() + "的文章待审核，请登录后台查看");
        }
        return new Response(200, "OK", contentService.addContent(record));
    }

    @DeleteMapping(value = "/deleteContent", params = {"cid"})
    public Response deleteContent(int cid) throws Exception {
        return new Response(200, "OK", contentService.deleteContentByCid(cid));
    }

    @PostMapping(value = "/updateContent")
    public Response updateContent(@RequestBody ContentInfo contentInfo) throws Exception {
        Content record = contentService.contentInfo2Content(contentInfo);
        return new Response(200, "OK", contentService.updateContentByCid(record));
    }

    @PostMapping(value = "/pass")
    public Response pass(@RequestBody ContentInfo contentInfo) throws Exception {
        Content record = contentService.contentInfo2Content(contentInfo);
        record.setStatus(contentInfo.getStatus());
        return new Response(200, "OK", contentService.pass(record));
    }

    @GetMapping(value = "/getContentByCid", params = {"cid"})
    public Response getContentByCid(int cid) throws Exception {
        return new Response(200, "OK", contentService.getContentByCid(cid));
    }

    @GetMapping(value = "/wantDelete", params = {"cid"})
    public Response wantDelete(int cid) throws Exception {
        ContentInfo contentInfo = contentService.getContentByCid(cid);
        if("1".equals(optionsService.getNotify()))
            notifyService.sendNotify(contentInfo.getAuthor()+"申请删除文章",
                    contentInfo.getAuthor() + "申请删除《" + contentInfo.getTitle()
                            + "》，已经修改文章为审核状态，请登录后台查看");
        Content content = contentService.contentInfo2Content(contentInfo);

        return new Response(200, "OK", contentService.updateContentByCid(content));
    }
}
