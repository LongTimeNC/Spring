package com.samlfatcat.aop;


import com.samlfatcat.utils.Connectionlocal;
import com.samlfatcat.utils.JdbcUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author zsz
 * @Description
 * @date 2022/6/7
 */
@Component//Spring当作Bean
@Aspect//当作AOP
public class AopHander {
    @Pointcut("execution(void com.samlfatcat.service.EmpServiceImpl.selectServiceEmp())")
    public void fun(){}
//    Connection connection = JdbcUtils.getConnection();
//    //前置
//    @Before("fun()")
//    public void method1(){
//        try {
//            connection.setAutoCommit(false);
//            connection.commit();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//    //后置
//    @After("fun()")
//    public void method2(){
//        try {
//            connection.rollback();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }finally {
//            JdbcUtils.release(connection,null,null);
//        }
//    }
    @Around("fun()")
    public void method(ProceedingJoinPoint joinPoint){
        Connection connection = JdbcUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            joinPoint.proceed();
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            JdbcUtils.release(connection,null,null);
        }

    }
}
