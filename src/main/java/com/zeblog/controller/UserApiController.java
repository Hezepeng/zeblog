package com.zeblog.controller;

import com.zeblog.common.ServerResponse;
import com.zeblog.entity.User;
import com.zeblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

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
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ServerResponse<User> login(String username, String password, HttpSession httpSession) {
        return userService.login(username, password);
    }

}
