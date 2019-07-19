package com.zeblog.service;

import com.zeblog.common.ServerResponse;
import com.zeblog.entity.User;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {

    ServerResponse<User> getUserDetail(String username);

    ServerResponse<List<User>> getAllUsers();

    ServerResponse<User> login(String username, String password);

    ServerResponse register(User user);

    ServerResponse deleteUser(HttpSession session, String username);

    ServerResponse updateUser(User user);


}
