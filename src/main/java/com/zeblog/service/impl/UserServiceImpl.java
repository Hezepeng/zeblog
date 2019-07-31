package com.zeblog.service.impl;

import com.zeblog.common.ServerResponse;
import com.zeblog.dao.UserMapper;
import com.zeblog.entity.User;
import com.zeblog.service.UserService;
import com.zeblog.util.MD5Util;
import com.zeblog.util.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hezepeng
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse<User> getUserInfo(HttpServletRequest request) {
        Integer userId = TokenUtil.getUserIdFromRequest(request);
        User user = userMapper.selectByPrimaryKey(userId);
        user.setPassword("");
        return ServerResponse.createBySuccess(user);
    }

    @Override
    public ServerResponse<List<User>> getAllUsers(HttpServletRequest request) {
        return ServerResponse.createBySuccess(userMapper.selectAll());
    }

    @Override
    public ServerResponse<Map<String, String>> login(String username, String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return ServerResponse.createByErrorMessage("用户名或密码不能为空！");
        }
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        password = MD5Util.getMD5Upper(password);
        user = userMapper.selectByUsernameAndPassword(username, password);
        if (user == null) {
            return ServerResponse.createByErrorMessage("密码错误");
        }
        // 记录登录时间、增加登录次数
        user.setLastLoginTime(new Date());
        user.setLoginTimes(user.getLoginTimes() + 1);
        userMapper.updateByUsernameSelective(user);
        // 签发token
        String token = TokenUtil.createJWT(user.getUserId().toString(), user.getUsername());
        Map<String, String> resultMap = new HashMap<>(10);
        resultMap.put("token", token);
        return ServerResponse.createBySuccess("登陆成功", resultMap);
    }

    @Override
    public ServerResponse register(User user) {
        if (checkUsernameExist(user.getUsername())) {
            return ServerResponse.createByErrorMessage("用户名已存在");
        }
        user.setPassword(MD5Util.getMD5Upper(user.getPassword()));
        user.setCreateTime(new Date());
        user.setLoginTimes(0);
        int effectRow = userMapper.insert(user);
        if (effectRow == 0) {
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccess("注册用户成功", user);

    }

    @Override
    public ServerResponse deleteUser(HttpServletRequest request, String username) {
        User user = userMapper.selectByUsername(TokenUtil.getUsernameByRequest(request));
        if (user.getUsername().equals(username)) {
            return ServerResponse.createByErrorMessage("您无法删除自己");
        }
        if (!checkUsernameExist(username)) {
            return ServerResponse.createByErrorMessage("要删除的用户不存在");
        }
        int effectRow = userMapper.deleteByUsername(username);
        if (effectRow == 0) {
            return ServerResponse.createByErrorMessage("删除用户失败");
        }
        return ServerResponse.createBySuccessMessage("删除用户成功");
    }

    @Override
    public ServerResponse updateUser(User user) {
        //部分属性设置为null  防止更改
        user.setPassword(null);
        user.setLoginTimes(null);
        user.setCreateTime(null);
        user.setRole(null);
        user.setLastLoginTime(null);
        int effectRow = userMapper.updateByUsernameSelective(user);
        if (effectRow == 0) {
            return ServerResponse.createByErrorMessage("信息修改失败");
        }
        return ServerResponse.createBySuccessMessage("信息修改成功");
    }

    @Override
    public ServerResponse checkPassword(HttpServletRequest request, String password) {
        String username = TokenUtil.getUsernameByRequest(request);
        password = MD5Util.getMD5Upper(password);
        User user = userMapper.selectByUsernameAndPassword(username, password);
        if (user != null) {
            return ServerResponse.createBySuccess("密码验证成功");
        }
        return ServerResponse.createByErrorMessage("旧密码验证失败");
    }

    @Override
    public ServerResponse checkUsername(String username) {
        if (checkUsernameExist(username)) {
            return ServerResponse.createByErrorMessage("用户名已被注册");
        }
        return ServerResponse.createBySuccess("用户名可用");
    }

    private boolean checkUsernameExist(String username) {
        User user = userMapper.selectByUsername(username);
        return user != null;
    }
}
