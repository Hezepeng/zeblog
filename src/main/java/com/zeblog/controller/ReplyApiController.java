package com.zeblog.controller;

import com.zeblog.common.ServerResponse;
import com.zeblog.entity.CommentReply;
import com.zeblog.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Hezepeng
 * @email: hezepeng96@foxmail.com
 * @date: 2019/8/23 13:33
 */

@Controller
@RequestMapping("api/reply/")
public class ReplyApiController {

    @Autowired
    private ReplyService replyService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addReply(HttpServletRequest request, @RequestBody CommentReply commentReply) {
        return replyService.addReply(request, commentReply);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse deleteReply(HttpServletRequest request, @RequestBody CommentReply commentReply) {
        return replyService.deleteReply(request, commentReply);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse updateReply(HttpServletRequest request, @RequestBody CommentReply commentReply) {
        return replyService.updateReply(request, commentReply);
    }

}

