package com.zeblog.service.impl;

import com.zeblog.common.Const;
import com.zeblog.common.ServerResponse;
import com.zeblog.dao.UserMapper;
import com.zeblog.entity.User;
import com.zeblog.service.UserService;
import com.zeblog.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse<User> getUserDetail(String username) {
        return ServerResponse.createBySuccess(userMapper.selectByUsername(username));
    }

    @Override
    public ServerResponse<List<User>> getAllUsers() {
        return ServerResponse.createBySuccess(userMapper.selectAll());
    }

    @Override
    public ServerResponse<User> login(String username, String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return ServerResponse.createByErrorMessage("用户名或密码不能为空！");
        }
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        user = userMapper.selectByUsernameAndPassword(username, password);
        if (user == null) {
            return ServerResponse.createByErrorMessage("密码错误");
        }
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登陆成功", user);
    }

    @Override
    public ServerResponse register(User user) {
        if (checkUsernameExist(user.getUsername())) {
            return ServerResponse.createByErrorMessage("用户名已存在");
        }
        user.setPassword(MD5Util.getMD5Upper(user.getPassword()));
        int effectRow = userMapper.insert(user);
        if (effectRow == 0) {
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccess("注册成功", user);

    }

    @Override
    public ServerResponse deleteUser(HttpSession session, String username) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (!user.getRole().equals(Const.ADMIN_ROLE)) {
            return ServerResponse.createByErrorMessage("您没有权限进行此操作");
        }
        if (user.getUsername().equals(username)) {
            return ServerResponse.createByErrorMessage("您无法删除自己");
        }
        if (!checkUsernameExist(username)) {
            return ServerResponse.createByErrorMessage("要删除的用户不存在");
        }
        int effectRow = userMapper.deleteByUsername(username);
        if (effectRow == 0) {
            return ServerResponse.createByErrorMessage("删除失败");
        }
        return ServerResponse.createBySuccessMessage("删除成功");
    }

    @Override
    public ServerResponse updateUser(User user) {
        //如果修改密码了，则加密
        if (user.getPassword() != null) {
            user.setPassword(MD5Util.getMD5Upper(user.getPassword()));
        }
        int effectRow = userMapper.updateByUsernameSelective(user);
        if (effectRow == 0) {
            return ServerResponse.createByErrorMessage("信息修改失败");
        }
        return ServerResponse.createBySuccessMessage("信息修改成功");
    }

    private boolean checkUsernameExist(String username) {
        User user = userMapper.selectByUsername(username);
        return user != null;
    }
}
