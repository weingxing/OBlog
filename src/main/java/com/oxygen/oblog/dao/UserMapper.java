package com.oxygen.oblog.dao;

import com.oxygen.oblog.dto.UserInfo;
import com.oxygen.oblog.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer uid);

    User selectByName(String name);

    UserInfo selectUserInfoByUid(Integer uid);

    UserInfo selectUserInfoByName(String username);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<UserInfo> selectPage();

    List<User> selectAllAdmin();

    int getAdminCount();
}