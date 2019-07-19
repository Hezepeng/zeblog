package com.zeblog.service.impl;

import com.zeblog.dao.UserMapper;
import com.zeblog.entity.User;
import com.zeblog.service.UserService;
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
