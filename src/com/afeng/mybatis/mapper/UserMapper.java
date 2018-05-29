package com.afeng.mybatis.mapper;

import com.afeng.mybatis.pojo.User;

import java.util.List;

/**
 * Mapper动态代理开发需要遵守的几个规则
 * 1.在Mapper.xml映射文件中 namespace必须要和接口类名全路径一致
 * 2.接口中的方法必须和namespace中的SQL的id一致
 * 3.接口中的方法与映射文件中SQL的输入类型和输出类型必须一致
 */
public interface UserMapper {
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
    void saveUser2(User user);


}
