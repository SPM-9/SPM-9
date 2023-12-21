package com.fxxkyiwangchangxue.yiwangnochangxue.service;

import java.io.*;

import com.fxxkyiwangchangxue.yiwangnochangxue.dao.SqlSessionFactoryUtils;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.UserMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.User;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

@WebServlet(name = "UserLogin", value = "/UserLogin")
public class UserLogin extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        if (userName == null || password == null)
            return;

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User loginUser = null;
        try (sqlSession) {
            loginUser = userMapper.selectByLogin(userName, password);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
            return;
        }
        if (loginUser == null) {
            pw.print("false");
        } else {
            HttpSession session = req.getSession(true);
            session.setAttribute("user", loginUser);
            Gson gson = new Gson();
            String json = gson.toJson(loginUser);
            pw.print(json);
        }

        sqlSession.close();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doPost(req, resp);
    }

    @Override
    public void destroy() {

    }
}