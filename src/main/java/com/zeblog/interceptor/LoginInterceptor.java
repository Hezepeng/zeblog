package com.zeblog.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zeblog.common.Const;
import com.zeblog.common.ResponseCode;
import com.zeblog.common.ServerResponse;
import com.zeblog.dao.UserMapper;
import com.zeblog.entity.User;
import com.zeblog.util.TokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * @author: Hezepeng
 * @email: hezepeng96@foxmail.com
 * @date: 2019-07-23 18:04
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 放通 options请求
        if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
            return true;
        }
        String token = request.getHeader("X-Token");
        // 解析token是否合法
        Claims claims = null;
        try {
            claims = TokenUtil.parseToken(token);
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            System.out.println(e.getMessage());
            // 要求重新登录
            sendErrorMsg(response);
            return false;
        }
        String userId = claims.getId();
        String username = claims.getSubject();
        String issuer = claims.getIssuer();
        Date expirationTime = claims.getExpiration();
        User user = userMapper.selectByUsername(username);
        Date now = new Date();
        if (user.getUserId().toString().equals(userId) && issuer.equals(Const.TOKEN_ISSUER) && now.before(expirationTime))
        {
            return true;
        } else{
            sendErrorMsg(response);
            return false;
        }
    }

    private void sendErrorMsg(HttpServletResponse response) {
        response.reset();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        String responseContent = JSONObject.toJSONString(ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "身份已过期，请重新登录"));
        System.out.println(responseContent);
        try {
            PrintWriter pw = response.getWriter();
            pw.write(responseContent);
            pw.flush();
            pw.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
