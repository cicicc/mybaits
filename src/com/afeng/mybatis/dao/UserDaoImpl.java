package com.afeng.mybatis.dao;

import com.afeng.mybatis.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl  implements UserDao{
    //注入SQLSessionFactory
    private SqlSessionFactory sqlSessionFactory;

    public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
        super();
        this.sqlSessionFactory = sqlSessionFactory;
    }

    /**
     * 根据用户id查询user
     * @param id 要查询用户的用户id
     * @return 查询所得结果
     */
    @Override
    public User queryUserById(int id) {
        //创建SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行查询逻辑
        return sqlSession.selectOne("queryUserById", id);
    }

    /**
     * 根据用户名模糊查询用户
     * @param username 用户名
     * @return 模糊查询所得用户名对象list
     */
    @Override
    public List<User> queryUserByUsername(String username) {
        //创建SQLSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行查询语句
        return  sqlSession.selectList("queryUserByUsername", "%" + username + "%");
    }

    /**
     * 保存传递过来的user对象
     * @param user 要保存的user对象
     */
    @Override
    public void saveUser(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.insert("saveUser", user);
        sqlSession.close();
    }
}
