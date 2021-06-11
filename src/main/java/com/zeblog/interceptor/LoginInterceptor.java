package com.zeblog.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.zeblog.common.Const;
import com.zeblog.common.ResponseCode;
import com.zeblog.common.ServerResponse;
import com.zeblog.dao.UserMapper;
import com.zeblog.entity.User;
import com.zeblog.util.TokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Objects;

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

        // 获取请求Headers中的token
        String token = request.getHeader(Const.TOKEN_HEADER_NAME);
        // 解析token是否合法
        Claims claims = null;
        try {
            claims = TokenUtil.parseToken(token);
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            // token过期 需要重新登录
            sendErrorMsg(response, ResponseCode.NEED_LOGIN.getCode(), "登录信息已过期，请重新登录");
            return false;
        } catch (io.jsonwebtoken.SignatureException e) {
            // token解析错误 非法的token
            sendErrorMsg(response, ResponseCode.NEED_LOGIN.getCode(), "身份验证失败，请重新登录");
        } catch (JwtException ex) {
            // 未知的其他错误
            sendErrorMsg(response, ResponseCode.NEED_LOGIN.getCode(), "身份验证异常，请重新登录");
        }
        if (claims == null) {
            return false;
        }

        String userId = claims.getId();
        String username = claims.getSubject();
        String issuer = claims.getIssuer();
        Date expirationTime = claims.getExpiration();
        User user = userMapper.selectByUsername(username);
        Date now = new Date();

        // 依靠@AdminInterceptor注解判断是否是Admin操作
        // 获取目标方法对象
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 获取改方法上的注解
        AdminInterceptor adminInterceptor = handlerMethod.getMethodAnnotation(AdminInterceptor.class);
        // 如果有@AdminInterceptor注解, 但不是管理员身份
        if (adminInterceptor != null && !user.getRole().equals(Const.ADMIN_ROLE)) {
            sendErrorMsg(response, ResponseCode.NO_PERMISSION.getCode(), "没有足够的权限，无法操作");
            return false;
        }

        if (user.getUserId().toString().equals(userId) && issuer.equals(Const.TOKEN_ISSUER) && now.before(expirationTime)) {
            return true;
        } else {
            sendErrorMsg(response, ResponseCode.NEED_LOGIN.getCode(), "身份验证失败，请重新登录");
            return false;
        }
    }

    private void sendErrorMsg(HttpServletResponse response, int errorCode, String msg) {
        response.reset();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        String responseContent = JSONObject.toJSONString(ServerResponse.createByErrorCodeMessage(errorCode, msg));
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
