package com.samlfatcat.service;

import com.samlfatcat.dao.EmpDao;
import com.samlfatcat.dao.EmpDaoImpl;
import com.samlfatcat.utils.JdbcUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.sql.Connection;

/**
 * @author zsz
 * @Description
 * @date 2022/6/1
 */
//<bean id="empService" class="com.samlfatcat.aop.com.samllfatcat.service.EmpServiceImpl">
//@Component("userService")
@Service("empService")//service层注解
@Scope("singleton")//单例的
public class EmpServiceImpl implements EmpService{
    //<property name="empDao" ref="empDao"></property>-->
//    @Autowired//按照数据类型ByType从Spring容器中匹配
//    @Qualifier("empDao")//按照名字Byname从Spring容器中匹配,Qualifier要结合Autowired一起使用
    @Resource(name = "empDao")//Resource相当于Qualifier+Autowired
    EmpDao empDao;
    //注解方式set方法可以省略不写
//    public void setEmpDao(EmpDao empDao) {
//        this.empDao = empDao;
//    }

    @Value("${jdbc.url}")
    private String driver;

    public void selectServiceEmp() {
//        empDao.selectEmp();
        empDao.update1();
//        int a = 1/0;
        empDao.update2();

//        System.out.println(driver);
//        System.out.println("selectServiceEmp......");
    }

    public void save(){
        System.out.println("save running......");
    }

    //创建初始方法和销毁方法
    //初始化注解 : PostConstruct
    @PostConstruct
    public void init(){
        System.out.println("service对象的初始化方法");
    }
    //销毁注解 : PreDestroy
    @PreDestroy
    public void destory(){
        System.out.println("service对象的销毁方法");
    }

}
