package com.cn.web.ui.mini.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class MysqlUtile {
    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://60.205.168.159:3306/pc";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "123456";


    public static int GetRandomNum(int num1, int num2) {
        int result = (int) (num1 + Math.random() * (num2 - num1 + 1));
        return result;
    }



    public static Connection getConnectionJD() {
        Connection conn = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
//    public static void main(String[] args) {
//        System.out.println(new MysqlUtile().getGardenJD());
//    }

}
