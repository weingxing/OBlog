package com.oxygen.oblog.controller;


import com.oxygen.oblog.dto.PageRequest;
import com.oxygen.oblog.dto.Response;
import com.oxygen.oblog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 后台管理控制类
 * @author Oxygen
 * @since 2020/09/27
 */
@Controller
public class AdminController {
    @Autowired
    private OptionsService optionsService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ContentService contentService;
    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String admin() throws Exception {
        return "admin";
    }

    @GetMapping("/welcome")
    @ResponseBody
    public Response welcome() throws Exception {
        PageRequest request = new PageRequest(1, 10);
        long reviewedComment = commentService.getReviewedCommentPage(request).getTotal();
        long reviewedContent = contentService.getReviewedContentByPage(request).getTotal();
        long publicComment = commentService.getPublicCommentPage(request).getTotal();
        long publicContent = contentService.getPublicContentByPage(request).getTotal();
        String mid = optionsService.getMusic();
        String email = userService.getUserByUid(1).getEmail();
        String res = "{\"reviewed_comment\": " + reviewedComment + "," +
                "\"reviewed_content\": " + reviewedContent + "," +
                "\"public_comment\": " + publicComment + "," +
                "\"public_content\": " + publicContent + "," +
                "\"music\": " + mid + "," +
                "\"email\": \"" + email + "\"" +
                "}";
        return new Response(200, "", res);
    }

    @GetMapping(value = "/user_welcome", params = {"aid", "author"})
    @ResponseBody
    public Response userWelcome(int aid, String author) throws Exception {
        PageRequest request = new PageRequest(1, 10);
        long content = contentService.getMyContentByPage(aid, request).getTotal();
        long comment = commentService.getMyComment(author, request).getTotal();
        String mid = optionsService.getMusic();
        String email = userService.getUserByUid(1).getEmail();
        String res = "{\"comment\": " + comment + "," +
                "\"content\": " + content +  "," +
                "\"music\": " + mid + "," +
                "\"email\": \"" + email + "\"" +
                "}";
        return new Response(200, "OK", res);
    }
}
