package com.fxxkyiwangchangxue.yiwangnochangxue.service;

import java.io.*;

import com.fxxkyiwangchangxue.yiwangnochangxue.dao.SqlSessionFactoryUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.ibatis.session.SqlSession;

@WebServlet(name = "GetNotifications", value = "/GetNotifications")
public class GetNotifications extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        String cnt = req.getParameter("notifCount");
        if (cnt == null)
            return;
        int count = Integer.parseInt(cnt);

        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();



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