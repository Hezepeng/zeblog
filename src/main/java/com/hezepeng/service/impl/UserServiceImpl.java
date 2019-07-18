package com.hezepeng.service.impl;

import com.hezepeng.dao.UserMapper;
import com.hezepeng.entity.User;
import com.hezepeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAllUsers() {
        return userMapper.selectAll();
    }
}
