package com.afeng.mybatis.mapper;

import com.afeng.mybatis.pojo.Orders;

import java.util.List;

public interface OrderMapper {
    /**
     * 查询所有的订单
     * @return 存储着查询结果的订单list
     */
    List<Orders> queryAllOrder();

    /**
     * 一对一查询的调用　查询所有的订单信息　及其对应的用户部分信息
     * 使用左连接的方式
     * @return 查询到的结果集　
     */
    List<Orders> queryOrderUser();
}
