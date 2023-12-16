package com.fxxkyiwangchangxue.yiwangnochangxue.service.todo;

import java.io.*;
import java.util.Enumeration;
import java.util.List;

import com.fxxkyiwangchangxue.yiwangnochangxue.dao.SqlSessionFactoryUtils;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.StudyTaskMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.StudyTask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.ibatis.session.SqlSession;

@WebServlet(name = "GetTodos", value = "/GetTodos")
public class GetTodos extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        String userIdString = req.getParameter("userId");
        if (userIdString == null) {
            resp.setStatus(500);
            return;
        }
        int userId = Integer.parseInt(userIdString);

//        Enumeration<String> headerNames = req.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String k = headerNames.nextElement();
//            System.out.println(k + ": " + req.getHeader(k));
//        }

        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        StudyTaskMapper mapper = sqlSession.getMapper(StudyTaskMapper.class);
        List<StudyTask> todoTasks = mapper.selectTodoStudyTask(userId);
        sqlSession.close();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        String json = gson.toJson(todoTasks);

        pw.print(json);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doPost(req, resp);
    }

    @Override
    public void destroy() {

    }
}