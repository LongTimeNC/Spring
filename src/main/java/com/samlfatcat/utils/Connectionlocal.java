package com.samlfatcat.utils;

import java.sql.Connection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zsz
 * @Description
 * @date 2022/6/7
 */
public class Connectionlocal {
    public void Connectionlocal(){}

    //1------数据库连接池
    public static Map<Long, Connection> pool = new ConcurrentHashMap<Long, Connection>();

    //将数据库连接放到连接池中
    public static void putConnection(Connection connection){
        //获取当前线程编号
        long id = Thread.currentThread().getId();
        //添加到map集合里面
        pool.put(id,connection);
    }

    //将连接从连接池中取出来
    public static Connection getConnection(){
        //获取当前线程编号
        long id = Thread.currentThread().getId();
        Connection connection = pool.get(id);
        return connection;
    }

    //从连接池中移除连接
    public static void removeConnection(){
        //获取当前线程编号
        long id = Thread.currentThread().getId();
        pool.remove(id);
    }
}

