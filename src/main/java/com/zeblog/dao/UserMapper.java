package com.zeblog.dao;

import com.zeblog.entity.User;
import java.util.List;

/**
 * @author zeblog
 */
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}