package com.fxxkyiwangchangxue.yiwangnochangxue.service;

import java.io.*;

import com.fxxkyiwangchangxue.yiwangnochangxue.dao.SqlSessionFactoryUtils;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.UserMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.FinalValues;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

@WebServlet(name = "UserRegister", value = "/UserRegister")
public class UserRegister extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        String userName = req.getParameter("userName");
        String userEmail = req.getParameter("userEmail");
        String password = req.getParameter("password");
        String userNickName = req.getParameter("userNickName");

        if (userName == null || userEmail == null || password == null || userNickName == null)
            return;

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 先检查用户是否存在
        User user = userMapper.selectByUserName(userName);
        if (user != null) {
            pw.print("false");
        } else {
            user = new User(userName, password, userNickName, userEmail);
            userMapper.insertByRegister(user);
            sqlSession.commit();
            int id = user.getUid();
        }
        sqlSession.close();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        doPost(req, resp);
    }

    @Override
    public void destroy() {

    }
}