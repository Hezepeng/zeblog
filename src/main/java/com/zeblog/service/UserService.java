package com.zeblog.service;

import com.zeblog.common.ServerResponse;
import com.zeblog.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    ServerResponse<User> login(String username, String password);
}
