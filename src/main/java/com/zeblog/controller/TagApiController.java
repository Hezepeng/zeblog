package com.zeblog.controller;


import com.zeblog.common.ServerResponse;
import com.zeblog.entity.ArticleTag;
import com.zeblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: Hezepeng
 * @email: hezepeng96@foxmail.com
 * @date: 2019-07-31 17:18
 */
@Controller
@RequestMapping("api/tag")
public class TagApiController {

    @Autowired
    private TagService tagService;

    @ResponseBody
    @RequestMapping(value = "getUserTag",method = RequestMethod.GET)
    public ServerResponse<List<ArticleTag>> getUserTag(HttpServletRequest request){
        return tagService.getUserTag(request);
    }

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ServerResponse addUserTag(HttpServletRequest request, @RequestBody ArticleTag tag){
        return tagService.addUserTag(request,tag);
    }

    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ServerResponse updateUserTag(HttpServletRequest request, @RequestBody ArticleTag tag){
        return tagService.updateUserTag(request,tag);
    }

    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ServerResponse deleteUserTag(HttpServletRequest request, @RequestBody ArticleTag tag) {
        return tagService.deleteUserTag(request, tag);
    }
}
