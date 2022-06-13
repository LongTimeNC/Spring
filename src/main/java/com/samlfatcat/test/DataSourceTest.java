package com.samlfatcat.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.samlfatcat.config.SpringConfiguration;
import com.samlfatcat.dao.EmpDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author zsz
 * @Description
 * @date 2022/6/4
 */
public class DataSourceTest {
    @Test
    //手动创建c3p0数据源----获取数据库连接
    public void fun1() throws PropertyVetoException, SQLException {
        //创建数据源对象
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        //设置基本的连接参数
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/emp");
        dataSource.setUser("root");
        dataSource.setPassword("woaini5488142");
        //获取数据库连接
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }
    @Test
    //手动创建druid数据源----获取数据库连接
    public void fun2() throws SQLException {
        //创建数据源对象
        DruidDataSource druidDataSource = new DruidDataSource();
        //设置基本的连接参数
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/emp");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("woaini5488142");
        //获取数据库连接
        DruidPooledConnection connection = druidDataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }
    @Test
    //手动创建c3p0数据源----获取数据库连接(加载propertries配置文件形式的)
    public void fun3() throws PropertyVetoException, SQLException {
        //先读取配置文件
        ResourceBundle rb = ResourceBundle.getBundle("jdbc");
        String driver = rb.getString("jdbc.driver");
        String url = rb.getString("jdbc.url");
        String username = rb.getString("jdbc.username");
        String password = rb.getString("jdbc.password");
        //创建数据源对象 设置连接参数
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driver);
        dataSource.setJdbcUrl(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }
    @Test
    //测试Spring容器产生数据源对象(c3p0数据源)
    public void fun4() throws SQLException {
        //加载配置文件
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        //获取配置源对象
        //ComboPooledDataSource dataSource = (ComboPooledDataSource)app.getBean("dataSource");
        DataSource dataSource = app.getBean(DataSource.class);
        //获取连接
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        connection.close();
    }

    @Test
    //新注解配置Spring Configuration核心配置类方式 引入外部properties配置类生成数据源
    //专门加载核心配置类！AnnotationConfigApplicationContext
    public void fun5() throws SQLException {
        ApplicationContext app = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        EmpDao empDao = app.getBean(EmpDao.class);
        //通过Bean id获取外部properties配置类 方法的返回值 数据源
        DataSource dataSource = (DataSource)app.getBean("dataSource");
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        empDao.selectEmp();
    }
}
