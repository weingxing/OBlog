package com.oxygen.oblog.service.impl;


import com.oxygen.oblog.dao.UserMapper;
import com.oxygen.oblog.dto.UserInfo;
import com.oxygen.oblog.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * SpringSecurity 服务接口实现类实现
 * @author Oxygen
 * @since 2020/09/27
 */
@Service
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo user = userMapper.selectUserInfoByName(s);
        UserDetails userDetails = null;
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        // 如果用户不存在，抛出去异常，存在则构建userDetails
        if (user == null)
            throw new UsernameNotFoundException("用户不存在");
        else {
            authorities.add(new SimpleGrantedAuthority(user.getRight()));
            userDetails = User.withUsername(user.getUserName())
                    .password(user.getPassword())
                    .authorities(authorities).build();
        }

        return userDetails;
    }

    @Override
    public UserInfo getUserInfo() throws ClassCastException {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        UserDetails details = null;
        UserInfo user = null;
        if (principal instanceof UserDetails) {
            details = (UserDetails) principal;
            user = userMapper.selectUserInfoByName(details.getUsername());
            user.setPassword(null);
        }

        return user;
    }
}
