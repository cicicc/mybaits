package com.afeng.mybaits.test;

import org.junit.Test;

import java.sql.*;

public class TestJdbc {
    /**
     * 使用原生jdbc书写第一个测试文件
     * 以便和后面的mybaits开发进行比较
     */
    @Test
    public void testJdbc() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //加载数据库的驱动
            Class.forName("com.mysql.jdbc.Driver");
            //通过驱动管理类获取数据库连接
            connection = DriverManager.getConnection("jdbc:mysql:///mybaits", "root", "chenfeng980320");
            //书写SQL语句
            String sql = "select * from orders";
            //获得语句执行者对象
            preparedStatement = connection.prepareStatement(sql);
            //获得结果集对象
            resultSet = preparedStatement.executeQuery();
            //遍历结果集对象
            while (resultSet.next()) {
                System.out.println(resultSet.getString(4));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //在finally中释放资源
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    if (preparedStatement != null) {
                        try {
                            preparedStatement.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }finally {
                            if (connection != null) {
                                try {
                                    connection.close();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
