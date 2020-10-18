package com.oxygen.oblog.controller;


import com.github.pagehelper.PageInfo;
import com.oxygen.oblog.config.CommonConfig;
import com.oxygen.oblog.dto.LayuiPage;
import com.oxygen.oblog.dto.PageRequest;
import com.oxygen.oblog.dto.Response;
import com.oxygen.oblog.dto.UserInfo;
import com.oxygen.oblog.entity.User;
import com.oxygen.oblog.service.UserService;
import com.oxygen.oblog.util.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制类
 * @author Oxygen
 * @since 2020/09/27
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/addUser")
    public Response addUser(@RequestBody User user) throws Exception {
        user.setRightId(2);
        return new Response(200, "OK", userService.addUser(user));
    }

    @PostMapping(value = "/addUserAdmin")
    public Response addUserAdmin(@RequestBody User user) throws Exception {
        return new Response(200, "OK", userService.addUser(user));
    }

    @GetMapping(value = "/getAllUser", params = {"page", "limit"})
    public LayuiPage getUserInfoByPage(int page, int limit) throws Exception {
        PageRequest request = new PageRequest(page, limit);
        PageInfo<UserInfo> p = userService.getUserInfoByPage(request);
        return new LayuiPage(0, "", p.getTotal(), p.getList());
    }

    @GetMapping(value = "/getUserByUid", params = {"uid"})
    public Response getUserByUid(int uid) throws Exception {
        return new Response(200, "OK", userService.getUserInfoByUid(uid));
    }

    @DeleteMapping(value = "/deleteUser", params = {"uid"})
    public Response deleteUser(int uid) throws Exception {
        return new Response(200, "OK", userService.deleteUserByUid(uid));
    }

    @PostMapping("/updateUser")
    public Response updateUser(@RequestBody User user) throws Exception {
        return new Response(200, "OK", userService.updateUser(user));
    }

    @PostMapping(value = "/updatePassword", params = {"uid", "oldPassword", "password"})
    public Response updatePassword(int uid, String oldPassword, String password) throws Exception {
        oldPassword = RSAUtil.decrypt(CommonConfig.privateKey, oldPassword);
        assert oldPassword != null;
        boolean old = new BCryptPasswordEncoder()
                .matches(oldPassword, userService.getUserByUid(uid).getPassword());
        if (!old)
            return new Response(200, "OK", false);

        User user = new User();
        user.setUid(uid);
        password = RSAUtil.decrypt(CommonConfig.privateKey, password);
        assert password != null;
        user.setPassword(password);
        return new Response(200, "OK", userService.updateUser(user));
    }

    @PostMapping(value = "/testUsername", params = {"username"})
    public Response testUsername(String username) throws Exception {
        if(userService.getUserByUsername(username) == null)
            return new Response(200,"OK", true);
        return new Response(200,"OK", false);
    }
}
