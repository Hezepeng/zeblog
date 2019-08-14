package com.zeblog.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zeblog.common.ServerResponse;
import com.zeblog.service.CommonService;
import com.zeblog.util.TencentCloudUtil;
import org.springframework.stereotype.Service;

/**
 * @author: Hezepeng
 * @email: hezepeng96@foxmail.com
 * @date: 2019-08-12 17:30
 */
@Service
public class CommonServiceImpl implements CommonService {
    @Override
    public ServerResponse<JSONObject> getTencentTempToken() {
        JSONObject token = null;
        try {
            token = TencentCloudUtil.getTempToken();
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("获取TencentToken失败");
        }
        return ServerResponse.createBySuccess(token);
    }
}
