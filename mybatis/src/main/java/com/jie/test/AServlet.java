package com.jie.test;

import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AServlet",urlPatterns = "/AServlet")
public class AServlet extends HttpServlet {

    private SqlSession sqlSession=MybatisConfig.getSession();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String statement = "com.jie.test.UserMapper.getUser";//映射sql的标识字符串
        //执行查询返回一个唯一user对象的sql
        User user = sqlSession.selectOne(statement, 1);
        System.out.println(user);
        super.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }
}
