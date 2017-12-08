package com.cksmaster.user.mapper;


import com.cksmaster.user.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    void insertUser(User user);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    User findByUsername(String username);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}