package com.zeblog.service;

import com.zeblog.common.ServerResponse;
import com.zeblog.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface UserService {

    ServerResponse<User> getUserInfo(HttpServletRequest request);

    ServerResponse<List<User>> getAllUsers(HttpServletRequest request);

    ServerResponse<Map<String, String>> login(String username, String password);

    ServerResponse register(User user);

    ServerResponse deleteUser(HttpSession session, String username);

    ServerResponse updateUser(User user);

    ServerResponse checkPassword(HttpServletRequest request,String password);

}
