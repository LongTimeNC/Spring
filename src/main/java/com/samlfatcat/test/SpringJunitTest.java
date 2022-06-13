package com.samlfatcat.test;

import com.samlfatcat.config.SpringConfiguration;
import com.samlfatcat.dao.EmpDao;
import com.samlfatcat.service.EmpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author zsz
 * @Description
 * @date 2022/6/8
 * --------------Spring 集成 junit 方便测试
 */

@RunWith(SpringJUnit4ClassRunner.class)
//配置文件形式
//@ContextConfiguration("classpath:applicationContext.xml")
//新注解 核心配置类方式
@ContextConfiguration(classes = {SpringConfiguration.class})
public class SpringJunitTest {

    @Autowired
    EmpService empService;
    @Autowired
    DataSource dataSource;
    @Autowired
    EmpDao empDao;

    @Test
    public void test1() throws SQLException {
        empService.save();
        empDao.selectEmp();
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

}
