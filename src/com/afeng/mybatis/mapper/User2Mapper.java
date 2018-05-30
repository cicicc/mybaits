package com.afeng.mybatis.mapper;

import com.afeng.mybatis.pojo.QueryVo;
import com.afeng.mybatis.pojo.User;

import java.util.List;


/**
 * 根据包装类查询用户信息
 */
public interface User2Mapper {
    /**
     * 根据queryVoid包装类对象 在数据库中进行对应的查询
     * @param vo 包装类对象
     * @return user集合
     */
    User selectUserByQueryVo(QueryVo vo);

    /**
     * 查询数据库中数据的数目
     * @return 数据总条数
     */
    Integer queryUserCount();

    /**
     * 根据用户名模糊查询 并封装数据到QueryVo对象中的list集合
     * @param username 模糊查询的用户名
     * @return 封装好数据的QueryVo对象
     */
    List<QueryVo> selectQueryVoByUsername(String username);

    /**
     *根据QueryVo中的id列表 查询数据库
     * @param queryVo 封装了查询id的QueryVo对象
     * @return 查询到的User列表
     */
    List<User> selectQueryVoByIds(QueryVo queryVo);
}
