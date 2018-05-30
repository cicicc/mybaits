package com.afeng.mybatis.test;

import com.afeng.mybatis.mapper.OrderMapper;
import com.afeng.mybatis.mapper.User2Mapper;
import com.afeng.mybatis.pojo.Orders;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 用于测试order的映射文件配置是否正确
 */
public class TestOrderMapper {
    private SqlSessionFactory sqlSessionFactory;

    /**
     * 在此类中test方法执行之前进行执行
     * 此方法用途:
     *      做好初始化SQLSessionFactory的工作
     */
    @Before
    public void init() throws IOException {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        sqlSessionFactory =  sqlSessionFactoryBuilder.build(inputStream);
    }

    /**
     * 查询所有订单信息
     *      查询所得的userID都是空
     *      因为我们的pojo类中的用户id和数据库中不一致
     *      此时需要使用到resultMap
     *      修改了OrderMapper.xml后可以正常进行打印id了
     */
    @Test
    public void testQueryAllOrder(){
        //创建sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获得Mapper对象
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        List<Orders> orders = orderMapper.queryAllOrder();
//        System.out.println(orders);
        for (Orders order : orders) {
            System.out.println(order);
        }
        sqlSession.close();
    }

    /**
     * 查询订单用户
     */
    @Test
    public void queryOrderUser() {
        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获得对应的Mapper对象
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        List<Orders> ordersList = orderMapper.queryOrderUser();
        System.out.println(ordersList);
        sqlSession.close();
    }
}
