package com.oxygen.oblog.service;

import com.github.pagehelper.PageInfo;
import com.oxygen.oblog.dto.PageRequest;
import com.oxygen.oblog.dto.UserInfo;
import com.oxygen.oblog.entity.User;

import java.util.List;

/**
 * 用户服务类接口
 * @author Oxygen
 * @since 2020/09/27
 */
public interface UserService {

    public boolean addUser(User user) throws Exception;

    public boolean deleteUserByUid(int uid) throws Exception;

    public User getUserByUid(int uid) throws Exception;

    public User getUserByUsername(String username) throws Exception;

    public boolean updateUser(User user) throws Exception;

    public UserInfo getUserInfoByUid(int uid) throws Exception;

    public PageInfo<UserInfo> getUserInfoByPage(PageRequest request) throws Exception;
}
