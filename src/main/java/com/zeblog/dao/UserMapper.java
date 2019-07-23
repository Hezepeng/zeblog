package com.zeblog.dao;

import com.zeblog.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int deleteByUsername(String username);

    int insert(User record);

    User selectByPrimaryKey(Integer userId);

    List<User> selectAll();

    int updateByUsernameSelective(User record);

    User selectByUsername(String username);

    User selectByUsernameAndPassword(@Param("username")String username,@Param("password")String password);
}