package com.samlfatcat.utils;

import java.sql.*;

/**
 * @author zsz
 * @Description
 * @date 2022/6/7
 */
public class JdbcUtils {
    //注册驱动
    //通过静态代码块实现注册驱动
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //获取连接
    //方法声明称静态的好直接通过类名调用方法
    public static Connection getConnection(){
        //先到连接池中查看当前线程是否有一个连接
        Connection connection = Connectionlocal.getConnection();
        if(connection == null){
            //如果是空说明当前线程还没有建立连接
            try {
                //自己创建一个连接
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp","root","woaini5488142");
                //将连接放到连接池中
                Connectionlocal.putConnection(connection);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return connection;
    }

    //关闭连接，释放资源
    //方法声明称静态的好直接通过类名调用方法
    //无返回值，三个参数
    public static void release(Connection connection, Statement statement, ResultSet resultSet){
        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throw new RuntimeException("关闭resultSet错误");
            }
        }
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throw new RuntimeException("关闭statement错误");
            }
        }
        if(connection != null){
            try {
                //移除连接
                Connectionlocal.removeConnection();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throw new RuntimeException("关闭connection错误");
            }
        }
    }
}
