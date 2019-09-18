package com.zeblog.controller;

import com.zeblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public ModelAndView Index(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home/index");
        modelAndView.addObject("userList", userService.getAllUsers(request));
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("redirect_to_vue")
    public String Redirect(HttpServletRequest request, HttpSession session){
        String token = session.getAttribute("token").toString();
        return "redirect:http://47.100.207.45/#/blog/home?token="+token;
    }

}
