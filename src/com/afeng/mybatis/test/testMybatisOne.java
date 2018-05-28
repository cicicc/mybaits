package com.afeng.mybatis.test;

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

public class testMybatisOne {

    /**
     * 在测试代码执行之前执行 是所有mybatis查询中必须要执行的几步骤
     * 1.创建sqlSessionFactoryBuilder对象
     * 2加载SQLMapConfig配置文件
     * 3.创建SQLSessionFactory对象
     */
    private SqlSessionFactory sqlSessionFactory = null;
    @Before
    public void init() throws IOException {
        // 1.创建sqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //加载配置文件
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory对象
         sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
    }

    /**
     *测试mybatis的查询功能
     * 步骤分为
     * 4.创建SQLSession对象
     * 5.执行SQLSession对象执行查询
     * 6.打印结果
     * 7.释放资源
     */
    @Test
    public void testQueryUserById(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = sqlSession.selectOne("queryUserById", 1);
        System.out.println(user);
        sqlSession.close();
    }

    /**
     * 两种方式查询通过用户名 第一种方式
     * 通过占位方式进行相对应的替换
     * 在xml文件中仍旧使用#{v} 进行占位操作
     * 这种查询方式可以有效防止SQL注入
     * mybatis使用的是ognl表达式
     */
    @Test
    public void testQueryUserByUsername(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        String nameFragment = "王";
        List<User> list = sqlSession.selectList("queryUserByUsername","%" + nameFragment + "%");
        for (User user : list) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    /**
     * 通过拼接方式进行SQL的查询 在xml文件中必须要使用${value} 进行占位
     */
    @Test
    public void testQueryUserByUsername2(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        String nameFragment = "明";
        List<User> list = sqlSession.selectList("queryUserByUsername2",nameFragment );
        for (User user : list) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    /**
     * 实现用户的增加
     * 此时打印所得
     * User{id=0, username='老陈', sex='1', birthday=Mon May 28 17:19:22 CST 2018, address='科技大道'}
     */
    @Test
    public void saveUser(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setUsername("老陈");
        user.setSex("1");
        user.setBirthday(new Date());
        user.setAddress("科技大道");
        sqlSession.insert("saveUser", user);
        //session提交以后相当于事务的提交 所以在进行增删改的操作的时候 需要有commit语句 否则更改无法实现
        sqlSession.commit();
        //此时事务提交以后 该对象由原来的游离态转变为了持久态 但是在打印的时候无法获取到真是的id值
        System.out.println(user);
        sqlSession.close();
    }


    /**
    * 并且使得返回正常的用户id
     * 在原先的sql语句执行之后增加一句 select LAST_INSERT_ID
     * 此时打印所得
     * User{id=30, username='胖纯', sex='2', birthday=Mon May 28 17:18:45 CST 2018, address='科技大道'}
     */
    @Test
    public void saveUser2(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setUsername("胖纯");
        user.setSex("2");
        user.setBirthday(new Date());
        user.setAddress("科技大道");
        sqlSession.insert("saveUser2", user);
        //session提交以后相当于事务的提交 所以在进行增删改的操作的时候 需要有commit语句 否则更改无法实现

        sqlSession.commit();
        System.out.println(user);
        sqlSession.close();
    }

    /**
     * 根据用户id修改用户数据
     */
    @Test
    public void updateUserByUserId(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = sqlSession.selectOne("queryUserById", 16);
        user.setUsername("胖纯");
        sqlSession.update("updateUserByUserId",user);
        //session提交以后相当于事务的提交 所以在进行增删改的操作的时候 需要有commit语句 否则更改无法实现
        sqlSession.commit();
        System.out.println(user);
        sqlSession.close();
    }


    /**
     * 根据用户id将用户删除
     */
    @Test
    public void deleteUserByUserId(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.delete("deleteUserByUserId", 10);
        //session提交以后相当于事务的提交 所以在进行增删改的操作的时候 需要有commit语句 否则更改无法实现
        sqlSession.commit();
        sqlSession.close();
    }
}
