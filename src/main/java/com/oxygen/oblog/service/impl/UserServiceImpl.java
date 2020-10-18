package com.oxygen.oblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oxygen.oblog.config.CommonConfig;
import com.oxygen.oblog.dao.UserMapper;
import com.oxygen.oblog.dto.PageRequest;
import com.oxygen.oblog.dto.UserInfo;
import com.oxygen.oblog.entity.User;
import com.oxygen.oblog.service.UserService;
import com.oxygen.oblog.util.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 用户服务类实现
 * @author Oxygen
 * @since 2020/09/27
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean addUser(User user) throws Exception {
        String password = RSAUtil.decrypt(CommonConfig.privateKey, user.getPassword());
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        if (userMapper.selectByName(user.getUserName()) != null)
            return false;
        return userMapper.insert(user) > 0;
    }

    @Override
    public boolean deleteUserByUid(int uid) throws Exception {
        UserInfo u = userMapper.selectUserInfoByUid(uid);
        if ("admin".equals(u.getRight()) && userMapper.getAdminCount() == 1)
            return false;

        if ("admin".equals(u.getRight()) && uid == 1) {
            List<User> admins = userMapper.selectAllAdmin();
            User admin = admins.get(0);
            admin.setUid(1);
            if (userMapper.updateByPrimaryKeySelective(admin) > 0)
                return userMapper.deleteByPrimaryKey(uid) > 0;
            else
                return false;
        } else {
            return userMapper.deleteByPrimaryKey(uid) > 0;
        }
    }

    @Override
    public User getUserByUid(int uid) throws Exception {
        return userMapper.selectByPrimaryKey(uid);
    }

    @Override
    public User getUserByUsername(String username) throws Exception {
        return userMapper.selectByName(username);
    }

    @Override
    public boolean updateUser(User user) throws Exception {
        if (user.getPassword() != null) {
//            String password = RSAUtil.decrypt(CommonConfig.privateKey, user.getPassword());
//            assert password != null;
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        }
        User u = userMapper.selectByPrimaryKey(user.getUid());
        if(u.getRightId()== 1 && !user.getRightId().equals(u.getRightId()) && userMapper.getAdminCount() == 1 )
            return false;
        return userMapper.updateByPrimaryKeySelective(user) > 0;
    }

    @Override
    public UserInfo getUserInfoByUid(int uid) throws Exception {
        return userMapper.selectUserInfoByUid(uid);
    }

    @Override
    public PageInfo<UserInfo> getUserInfoByPage(PageRequest request) throws Exception {
        int pageNum = request.getPageNum();
        int pageSize = request.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<UserInfo> content = userMapper.selectPage();
        return new PageInfo<UserInfo>(content);
    }
}
