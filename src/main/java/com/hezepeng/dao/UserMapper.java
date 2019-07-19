package com.hezepeng.dao;

import com.hezepeng.entity.User;
import java.util.List;

/**
 * @author hezepeng
 */
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}