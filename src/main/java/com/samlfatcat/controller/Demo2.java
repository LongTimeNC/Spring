package com.samlfatcat.controller;

import com.samlfatcat.dao.EmpDao;
import com.samlfatcat.dao.EmpDaoImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zsz
 * @Description
 * @date 2022/6/6
 */
public class Demo2 {
    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        EmpDao empDao = (EmpDao)app.getBean("empDao");
        empDao.selectEmp();
    }
}
