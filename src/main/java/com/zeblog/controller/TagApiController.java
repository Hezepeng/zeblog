package com.zeblog.controller;


import com.zeblog.common.ServerResponse;
import com.zeblog.entity.Tag;
import com.zeblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "getUserTag",method = RequestMethod.GET)
    public ServerResponse<List<Tag>> getUserTag(HttpServletRequest request){
        return tagService.getUserTag(request);
    }
}
