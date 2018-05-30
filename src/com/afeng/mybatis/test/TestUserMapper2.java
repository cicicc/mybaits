package com.afeng.mybatis.test;

import com.afeng.mybatis.mapper.User2Mapper;
import com.afeng.mybatis.pojo.QueryVo;
import com.afeng.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TestUserMapper2 {

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
     * 查询用户通过queryVo对象封装的user对象
     */
    @Test
    public void selectUserByQueryVo(){
        //获取SQLSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper对象
        User2Mapper userMapper = sqlSession.getMapper(User2Mapper.class);
        //创建queryVo对象
        QueryVo queryVo = new QueryVo();
        User user = new User();
        user.setId(10);
        queryVo.setUser(user);
        //执行查询 根据QueryVo中的用户ID查询用户
        User user1 =  userMapper.selectUserByQueryVo(queryVo);
        System.out.println(user1);
        //查询数据总条数
        Integer count = userMapper.queryUserCount();
        System.out.println(count);
        sqlSession.close();
    }

    /**
     *错误的执行结果 NullPointerException
     * 因为没有使用resultMap进行正确的映射
     */
    @Test
    public void selectQueryVoByUsername() {
        //获取SQLSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取Mapper对象
        User2Mapper userMapper = sqlSession.getMapper(User2Mapper.class);
        String name = "陈";
        List<QueryVo> queryVos = userMapper.selectQueryVoByUsername(name);
        for (QueryVo queryVo : queryVos) {
            System.out.println(queryVo.getUser());
        }
        sqlSession.close();

    }


    /**
     * 根据QueryVo对象中的id查询数据库对象 获取查询结果
     */
    @Test
    public void selectQueryVoByIds() {
        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获得对应的Mapper对象
        User2Mapper user2Mapper = sqlSession.getMapper(User2Mapper.class);
        //创建QueryVo对象 并且对其中的ids进行赋值
        QueryVo queryVo = new QueryVo();
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(10);
        ids.add(24);
        queryVo.setIds(ids);
        List<User> users = user2Mapper.selectQueryVoByIds(queryVo);
        for (User user : users) {
            System.out.println(user);
        }
    }

}
