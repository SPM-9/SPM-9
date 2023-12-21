package com.fxxkyiwangchangxue.yiwangnochangxue.service;

import java.io.*;

import com.fxxkyiwangchangxue.yiwangnochangxue.dao.SqlSessionFactoryUtils;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.TeacherMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.UserMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.Teacher;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.User;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.ibatis.session.SqlSession;

@WebServlet(name = "TeacherLogin", value = "/TeacherLogin")
public class TeacherLogin extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        if (userName == null || password == null) {
            resp.setStatus(500);
            return;
        }

        Teacher teacher = new Teacher();
        teacher.setUserName(userName);
        teacher.setPassword(password);

        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        TeacherMapper teacherMapper = sqlSession.getMapper(TeacherMapper.class);

        try (sqlSession) {
            Teacher loginTeacher = teacherMapper.selectByLogin(teacher);
            if (loginTeacher == null) {
                pw.print("false");
            } else {
                HttpSession session = req.getSession(true);
                session.setAttribute("teacher", teacher);
                Gson gson = new Gson();
                String json = gson.toJson(loginTeacher);
                pw.print(json);
            }
        } catch (Exception e) {
            resp.setStatus(500);
            e.printStackTrace();
            return;
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doPost(req, resp);
    }

    @Override
    public void destroy() {

    }
}