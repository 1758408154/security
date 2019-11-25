package com.changes.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.changes.common.mapper.UserMapper;
import com.changes.common.model.User;
import com.changes.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author LiuJunJie
 * @since 2019/11/25 9:34
 */
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userMapper.selectOne(new LambdaQueryWrapper<User>(){
            {
                eq(User::getUsername,username);
            }
        });
        if(user == null){
            return null;
        }
        Collection<? extends GrantedAuthority> authorities = new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(username,user.getPassword(),authorities);
    }
}
