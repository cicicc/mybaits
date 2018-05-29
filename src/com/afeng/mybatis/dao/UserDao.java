package com.afeng.mybatis.dao;

import com.afeng.mybatis.pojo.User;

import java.util.List;

public interface UserDao {
    /**
     * 根据id查询用户
     */
    User queryUserById(int id);

    /**
     * 根据用户名进行模糊查询用户
     */
    List<User> queryUserByUsername(String username);

    /**
     * 保存用户
     */
    void saveUser(User user);
}
