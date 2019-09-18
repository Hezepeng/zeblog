package com.zeblog.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zeblog.common.ServerResponse;
import com.zeblog.entity.User;
import com.zeblog.interceptor.AdminInterceptor;
import com.zeblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public ServerResponse<Map<String, String>> login(@RequestBody User user) {
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
    @AdminInterceptor
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ServerResponse delete(HttpServletRequest request,@RequestBody User user) {
        return userService.deleteUser(request, user.getUsername());
    }

    @ResponseBody
    @RequestMapping(value = "info", method = RequestMethod.GET)
    public ServerResponse info(HttpServletRequest request) {
        return userService.getUserInfo(request);
    }

    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @AdminInterceptor
    public ServerResponse list(HttpServletRequest request) {
        return userService.getAllUsers(request);
    }

    @ResponseBody
    @RequestMapping(value = "checkPassword", method = RequestMethod.POST)
    public ServerResponse checkPassword(HttpServletRequest request, @RequestBody User user) {
        return userService.checkPassword(request, user.getPassword());
    }

    @ResponseBody
    @RequestMapping(value = "checkUsername", method = RequestMethod.POST)
    public ServerResponse checkUsername(HttpServletRequest request, String username) {
        return userService.checkPassword(request, username);
    }

    @ResponseBody
    @RequestMapping(value = "qqQuickLogin", method = RequestMethod.GET)
    public ServerResponse getTencentQuickLoginUrl(String redirect_url) {
        return userService.getTencentQuickLoginUrl(redirect_url);
    }

    @ResponseBody
    @RequestMapping(value = "qqQuickLoginCallback", method = RequestMethod.GET)
    public ServerResponse qqQuickLoginCallback(HttpServletRequest request,HttpSession session) {
        return userService.qqQuickLoginCallback(request,session);
    }
}
