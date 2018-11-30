package com.jie.test;

import java.sql.*;

public class JDBCLinkStart {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/yyjtest?useUnicode=true&characterEncoding=utf-8","root","root123");
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("select * from user ");
        while (resultSet.next()){
            System.out.println(resultSet.getLong(1));
        }
    }
}
