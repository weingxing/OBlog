package com.oxygen.oblog.controller;


import com.github.pagehelper.PageInfo;
import com.oxygen.oblog.dto.LayuiPage;
import com.oxygen.oblog.dto.PageRequest;
import com.oxygen.oblog.dto.Response;
import com.oxygen.oblog.entity.Comment;
import com.oxygen.oblog.service.CommentService;
import com.oxygen.oblog.service.NotifyService;
import com.oxygen.oblog.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 评论控制类
 * @author Oxygen
 * @since 2020/09/27
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private NotifyService notifyService;
    @Autowired
    private OptionsService optionsService;

    @GetMapping(value = "/getCommentByPage", params = {"cid", "page", "limit"})
    public LayuiPage getCommentByPage(int cid, int page, int limit) throws Exception {
        PageRequest request = new PageRequest(page, limit);
        PageInfo<Comment> p = commentService.getPublicCommentPageByCid(cid, request);
        return new LayuiPage(0, "", p.getTotal(), p.getList());
    }

    @GetMapping(value = "/getMyComment", params = {"author", "page", "limit"})
    public LayuiPage getMtComment(String author, int page, int limit) throws Exception {
        PageRequest request = new PageRequest(page, limit);
        PageInfo<Comment> p = commentService.getMyComment(author, request);
        return new LayuiPage(0, "", p.getTotal(), p.getList());
    }

    @DeleteMapping(value = "/deleteComment", params = {"coid"})
    public Response deleteComment(int coid) throws Exception {
        return new Response(200, "OK", commentService.deleteCommentByCoid(coid));
    }

    @GetMapping(value = "/getPublicComment", params = {"page", "limit"})
    public LayuiPage getPublicComment(int page, int limit) throws Exception {
        PageInfo<Comment> p = commentService.getPublicCommentPage(new PageRequest(page, limit));
        return new LayuiPage(0, "", p.getTotal(), p.getList());
    }

    @GetMapping(value = "/getReviewedComment", params = {"page", "limit"})
    public LayuiPage getReviewedComment(int page, int limit) throws Exception {
        PageInfo<Comment> p = commentService.getReviewedCommentPage(new PageRequest(page, limit));
        return new LayuiPage(0, "", p.getTotal(), p.getList());
    }

    @PostMapping("/addComment")
    public Response addComment(@RequestBody Comment comment) throws Exception {
        if("1".equals(optionsService.getNotify()))
            notifyService.sendNotify("文章有新的评论", comment.getText());
        return new Response(200, "OK", commentService.addComment(comment));
    }

    @GetMapping(value = "/passComment", params = {"coid"})
    public Response passComment(int coid) throws Exception {
        return new Response(200, "OK", commentService.passComment(coid));
    }

    @GetMapping(value = "/wantDelete", params = {"coid"})
    public Response wantDelete(int coid) throws Exception {
        Comment comment = commentService.getByCoid(coid);
        if("1".equals(optionsService.getNotify()))
            notifyService.sendNotify(comment.getAuthor()+"申请删除评论",
                    comment.getAuthor() + "申请删除评论：" + comment.getText()
                            + "，已经修改评论为审核状态，请登录后台查看");
        comment.setStatus("reviewed");
        return new Response(200, "OK", commentService.updateComment(comment));
    }

    @GetMapping(value = "/getCommentByCoid", params = {"coid"})
    public Response getCommentByCoid(int coid) throws Exception {
        return new Response(200, "OK", commentService.getByCoid(coid));
    }

    @PostMapping(value = "/updateComment")
    public Response update(@RequestBody Comment comment) throws Exception {
        return new Response(200, "OK", commentService.updateComment(comment));
    }
}
