package com.seehope.mapper;

import com.seehope.entity.Pro;
import com.seehope.entity.User;

import java.util.List;
import java.util.Map;


//创建public int register(User user)方法，
// 用于将数据存入数据库的user表，创建
// public User findUserByName(String username)方法
// 通过用户名称查找用户，如下：


public interface UserMapper {
    public int register(User user);
    public User findUserByName(String username);// 通过用户名称查找用户 ajax

    public User findUserByUserNameAndPassword(String username, String password); //注册添加新的用户和密码



}
