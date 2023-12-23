package com.fxxkyiwangchangxue.yiwangnochangxue.service.studytask;

import java.io.*;

import com.fxxkyiwangchangxue.yiwangnochangxue.dao.SqlSessionFactoryUtils;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.StudyTaskMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.StudyTask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.ibatis.session.SqlSession;

@WebServlet(name = "GetStudyTaskInfo", value = "/GetStudyTaskInfo")
public class GetStudyTaskInfo extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        String taskIdString = req.getParameter("taskId");
        if (taskIdString == null)
            return;
        int taskId = Integer.parseInt(taskIdString);

        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        StudyTaskMapper studyTaskMapper = sqlSession.getMapper(StudyTaskMapper.class);

        StudyTask task;
        try (sqlSession) {
            task = studyTaskMapper.SelectById(taskId);
            Long fileSize = studyTaskMapper.SelectFileSizeById(taskId);
            if (task == null) {
                resp.setStatus(404);
                return;
            }
            task.setFileSize(fileSize);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
            return;
        }

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        String json = gson.toJson(task, StudyTask.class);
        pw.print(json);
        resp.setStatus(200);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doPost(req, resp);
    }

    @Override
    public void destroy() {

    }
}