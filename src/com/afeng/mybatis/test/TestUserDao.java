package com.afeng.mybatis.test;

import com.afeng.mybatis.dao.UserDao;
import com.afeng.mybatis.dao.UserDaoImpl;
import com.afeng.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 测试UserDao代码是否可以进行正常操作
 */
public class TestUserDao {
    //创建全局的SQLSessionFactory对象
    private SqlSessionFactory sqlSessionFactory;
    /**
     * 在测试类运行之前运行
     */
    @Before
    public void init() throws IOException {
        //创建SQLSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //加载配置文件
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建sqlsessionFactory对象
        sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
    }

    /**
     * 测试userDaoImpl类中的功能是否可以正常执行
     */
    @Test
    public void queryUserById() {
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        User user = userDao.queryUserById(10);
        System.out.println(user);
        List<User> userList = userDao.queryUserByUsername("陈");
        System.out.println(userList);
        User user1 = new User();
        user1.setUsername("小胖胖");
        user1.setAddress("新街口");
        userDao.saveUser(user1);
        System.out.println(user1);
    }
}
