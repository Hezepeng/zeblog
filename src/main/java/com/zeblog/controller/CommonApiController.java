package com.zeblog.controller;

import com.alibaba.fastjson.JSONObject;
import com.zeblog.common.ServerResponse;
import com.zeblog.interceptor.AdminInterceptor;
import com.zeblog.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author: Hezepeng
 * @email: hezepeng96@foxmail.com
 * @date: 2019-08-12 17:25
 */
@Controller
@RequestMapping("api/common/")
public class CommonApiController {

    @Autowired
    CommonService commonService;

    @RequestMapping(value = "getTencentTempToken",method = RequestMethod.GET)
    @ResponseBody
    @AdminInterceptor
    public ServerResponse<JSONObject> getTencentToken(){
        return commonService.getTencentTempToken();
    }
}
