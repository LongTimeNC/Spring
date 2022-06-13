package com.samlfatcat.dao;

import com.samlfatcat.utils.JdbcUtils;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author zsz
 * @Description
 * @date 2022/6/1
 * --------------使用注解开发时需要在xml中配置组建扫描 作用是指定哪个包及其子包下的Bean需要进行扫描以便识别使用注解配置的类字段方法
 */
//<bean id="empDao" class="com.samlfatcat.aop.com.samllfatcat.com.samllfatcat.com.samllfatcat.pojo.com.samllfatcat.pojo.controller.com.samllfatcat.dao.EmpDaoImpl"></bean>
//@Component("empDao")
@Repository("empDao")//dao层注解
public class EmpDaoImpl implements EmpDao{
    public void selectEmp() {
        System.out.println(System.currentTimeMillis());
        System.out.println("selectEmp....");
    }
    public int update1(){
        int i = 0;
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("update account set money = money-100 where id = 1");
            i = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtils.release(null,preparedStatement,null);
        }
        return i;
    }
    public int update2(){
        int i = 0;
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("update account set money = money+100 where id = 2");
            i = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtils.release(null,preparedStatement,null);
        }
        return i;
    }
}
