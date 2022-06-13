package com.samlfatcat.aop;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author zsz
 * @Description
 * @date 2022/6/6
 */
@Component//Spring当作Bean
@Aspect//当作AOP
public class MyAdvice {
    //定义切入点 Pointcut：切点
    @Pointcut("execution(void com.samlfatcat.dao.EmpDao.selectEmp())")
    private void pt(){ }

    //共性功能
    //前置功能
    @Before("pt()")
    public void method(){
        System.out.println(System.currentTimeMillis());
    }
    //后置功能
    @After("pt()")
    public void fun(){
        System.out.println("你好世界");
    }
    //当业务方法发生异常执行，没有异常不执行
    @AfterThrowing(pointcut = "pt()",throwing = "ex")
    public void throwing(Exception ex){
        System.out.println("AfterThrowing....");
    }
    //成功返回时才会执行的方法
    @AfterReturning(pointcut = "pt()",returning = "retVal")
    public void doAfterReturnning(Object retVal){
        System.out.println("AfterReturning....");
    }

}
