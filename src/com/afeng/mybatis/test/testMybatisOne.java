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

public class testMybatisOne {

    /**
     * 在测试代码执行之前执行 是所有mybaits查询中必须要执行的几步骤
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
     *测试mybaits的查询功能
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
}
