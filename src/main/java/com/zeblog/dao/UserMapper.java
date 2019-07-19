package com.zeblog.dao;

import com.zeblog.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    User selectByPrimaryKey(String userId);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    String selectByUsername(String username);

    User selectByUsernameAndPassword(@Param("username")String username,@Param("password")String password);
}