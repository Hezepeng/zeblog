package com.zeblog.service;

import com.alibaba.fastjson.JSONObject;
import com.zeblog.common.ServerResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Hezepeng
 * @email: hezepeng96@foxmail.com
 * @date: 2019-08-12 17:29
 */
public interface CommonService {
    ServerResponse<JSONObject> getTencentTempToken();
}
