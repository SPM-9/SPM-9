package com.fxxkyiwangchangxue.yiwangnochangxue.service.studytask;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fxxkyiwangchangxue.yiwangnochangxue.dao.SqlSessionFactoryUtils;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.NotificationMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.StudyTaskMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.StudyTask;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.session.SqlSession;

@WebServlet(name = "UploadStudyTask", value = "/UploadStudyTask")
@MultipartConfig // 当发来的请求体是multipartbody的时候，需要给这个servlet加注解
public class UploadStudyTask extends HttpServlet {
    SimpleDateFormat sdf;

    @Override
    public void init() {
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        String teacherIdString = req.getParameter("teacherId");
        String taskTypeString = req.getParameter("taskType");
        String title = req.getParameter("title");
        String body = req.getParameter("body");
        String startTimeString = req.getParameter("startTime");
        String endTimeString = req.getParameter("endTime");
        Part filePart = req.getPart("file");
        if (teacherIdString == null || taskTypeString == null ||
                startTimeString == null || endTimeString == null) {
            resp.setStatus(500);
            return;
        }
        int teacherId = Integer.parseInt(teacherIdString);
        int taskType = Integer.parseInt(taskTypeString);

        Date startTime;
        Date endTime;
        try {
            startTime = sdf.parse(startTimeString);
            endTime = sdf.parse(endTimeString);
        } catch (ParseException e) {
            e.printStackTrace();
            resp.setStatus(500);
            return;
        }

        String fileName = null;
        byte[] file = null;
        if (filePart != null) {
            fileName = filePart.getSubmittedFileName();
            InputStream is = filePart.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            IOUtils.copy(bis, baos);
            file = baos.toByteArray();
        }

        StudyTask studyTask = new StudyTask();
        studyTask.setFori_teacherId(teacherId);
        studyTask.setTaskType(taskType);
        studyTask.setTitle(title);
        studyTask.setBody(body);
        studyTask.setFileName(fileName);
        studyTask.setFile(file);
        studyTask.setStartTime(startTime);
        studyTask.setEndTime(endTime);

        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        StudyTaskMapper studyTaskMapper = sqlSession.getMapper(StudyTaskMapper.class);
        NotificationMapper notificationMapper = sqlSession.getMapper(NotificationMapper.class);

        try (sqlSession) {
            studyTaskMapper.insertStudyTask(studyTask);
            int taskId = studyTask.getTaskId();
            notificationMapper.insertStudyTaskNotif(taskId);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
            sqlSession.rollback();
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