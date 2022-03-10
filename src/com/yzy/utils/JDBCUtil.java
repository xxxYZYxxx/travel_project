package com.yzy.utils;

import java.sql.*;

/**
 * 封装jdbc工具类
 * 1. 构造方法私有化 不希望工具类可以实例化对象
 * 2. 所有调用的方法用static关键字修饰 让其编程是类级别的方法 可以被类调用
 */
public class JDBCUtil {
    private JDBCUtil(){

    }
    /**
     * 把注册驱动的过程 写在静态代码块里 静态代码块在类加载的时候执行且只执行一次
     */
    static {
        //1. 注册驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取数据库连接
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        //2. 获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_test?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT","root","123456");

        return  connection;

    }
    //数据库操作对象不需要封装（因为会获取不同的对象和执行不同的sql语句）
    /**
     * 关闭数据库资源
     */
    public static void JDBCClose(Connection connection, Statement statement,ResultSet resultSet){
        //关闭结果集对象
        if(resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        //关闭数据库操作对象
        if(statement!=null){
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        //关闭数据库连接对象
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
