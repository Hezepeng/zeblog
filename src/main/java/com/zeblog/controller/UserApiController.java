package com.zeblog.controller;

import com.zeblog.common.ServerResponse;
import com.zeblog.entity.User;
import com.zeblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Hezepeng
 * @email: hezepeng96@foxmail.com
 * @date: 2019-07-19 15:38
 */
@Controller
@RequestMapping("api/user/")
public class UserApiController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ServerResponse<Map<String, String>> login(@RequestBody User user, HttpSession httpSession) {
        return userService.login(user.getUsername(), user.getPassword());
    }

    @ResponseBody
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ServerResponse register(@RequestBody User user) {
        return userService.register(user);
    }

    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ServerResponse update(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public ServerResponse delete(HttpSession session, String username) {
        return userService.deleteUser(session, username);
    }

    @ResponseBody
    @RequestMapping(value = "getUserInfo", method = RequestMethod.GET)
    public ServerResponse getUserInfo(HttpServletRequest request) {
        return userService.getUserInfo(request);
    }
}
