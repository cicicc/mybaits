package com.afeng.mybatis.test;

import com.afeng.mybatis.mapper.UserMapper;
import com.afeng.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * 测试Mapper动态代理进行开发的方式
 */
public class TestUserMapper {
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
     * 测试Mapper的书写是否正确 数据库中数据能否正常操作
     * 查询用户通过id值
     */
    @Test
    public void testSelectUserById(){
        //获取SQLSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //执行查询
        User user = userMapper.queryUserById(10);
        System.out.println(user);
        sqlSession.close();
    }

    /**
     * 查询用户通过username
     *              模糊查询
     */
    @Test
    public void testSelectUserByUsername(){
        //获取SQLSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //执行查询
        String username = "陈";
        List<User> userList = userMapper.queryUserByUsername("%" + username + "%");
        System.out.println(userList);
        sqlSession.close();
    }

    /**
     * 测试保存用户
     * 使用的是可查询用户保存后id的那种SQL
     */
    @Test
    public void testSaveUser(){
        //获取SQLSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //新建用户
        User user = new User();
        user.setUsername("小红");
        user.setBirthday(new Date());
        userMapper.saveUser2(user);
        System.out.println(user);
        sqlSession.commit();
        sqlSession.close();
    }

}
