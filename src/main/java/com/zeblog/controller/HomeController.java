package com.zeblog.controller;

import com.zeblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public ModelAndView Index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home/index");
        modelAndView.addObject("userList", userService.getAllUsers());
        return modelAndView;
    }
}
