package com.samlfatcat.controller;

import com.samlfatcat.dao.EmpDao;
import com.samlfatcat.dao.EmpDaoImpl;
import com.samlfatcat.service.EmpService;
import com.samlfatcat.service.EmpServiceImpl;
import com.samlfatcat.utils.Connectionlocal;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author zsz
 * @Description
 * @date 2022/6/1 Spring 两大特征 以 IOC AOP 为内核
 * -------------IOC (Inversion of Control) 控制反转
 * -------------使用对象时，由主动new产生对象转换为外部提供对象，此过程中对象创建控制权由程序转移到外部，就是控制反转
 * -------------Spring提供了一个IOC容器
 * -------------IOC容器负责对象的创建，初始化等一系列操作，被创建或被管理的对象在IOC容器中被称为Bean
 * -------------DI(dependency injection) 依赖注入
 * -------------在容器之间建立Bean与Bean之间的依赖关系 就是依赖注入
 *
 * -------------AOP (Aspect Oriented Programming) 面向切面编程
 * -------------作用在不改变原始设计的基础上为其进行功能增强的
 */
public class Demo {

    @Test
    //dao层
    public void fun1(){
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        EmpDao empDao = (EmpDao)app.getBean("empDao");
        empDao.selectEmp();
    }
    @Test
    //service层调用dao层
    public void fun2(){
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        EmpService empService = (EmpService)app.getBean("empService");
        empService.selectServiceEmp();
    }
    @Test
    //另一种加载配置文件的实现类---FileSystemXmlApplicationContext
    public void fun3(){
        ApplicationContext app = new FileSystemXmlApplicationContext("src/main/resources/applicationContext.xml");
        EmpDao empdao = (EmpDao)app.getBean("empDao");
        EmpService empService = app.getBean(EmpService.class);
        empService.selectServiceEmp();
        empdao.selectEmp();
    }
    @Test
    public void fun4(){
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        EmpDao empDao = app.getBean(EmpDao.class);
        empDao.selectEmp();
    }

    @Test
    //测试数据库事务
    public void fun5(){
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        EmpService empService = (EmpService)app.getBean("empService");
        empService.selectServiceEmp();
    }

}