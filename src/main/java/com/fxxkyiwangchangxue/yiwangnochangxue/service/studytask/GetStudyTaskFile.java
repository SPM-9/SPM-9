package com.fxxkyiwangchangxue.yiwangnochangxue.service.studytask;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.fxxkyiwangchangxue.yiwangnochangxue.dao.SqlSessionFactoryUtils;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.StudyTaskMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.StudyTask;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.session.SqlSession;

@WebServlet(name = "GetStudyTaskFile", value = "/GetStudyTaskFile")
public class GetStudyTaskFile extends HttpServlet {

    @Override
    public void init() {

    }

    /**
     * 发送文件Servlet，若学习任务没有附带文件则返回404
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/octet-stream");

        String taskIdString = req.getParameter("taskId");
        if (taskIdString == null)
            return;
        int taskId = Integer.parseInt(taskIdString);

        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        StudyTaskMapper studyTaskMapper = sqlSession.getMapper(StudyTaskMapper.class);

        StudyTask studyTask = studyTaskMapper.SelectFileById(taskId);
        sqlSession.close();
        // 没有文件则返回404
        if (studyTask.getFileName() == null || studyTask.getFile() == null) {
            resp.setStatus(404);
            return;
        }
        // 找到文件则发送
        String fileName = studyTask.getFileName();
        fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8); // 处理中文乱码

        // 发送文件
        resp.setHeader("Content-Disposition","attachment;filename=" + fileName);
        InputStream fileIn = new ByteArrayInputStream(studyTask.getFile());
        ServletOutputStream fileOut = resp.getOutputStream();

        IOUtils.copy(fileIn, fileOut);
        fileIn.close();
        fileOut.close();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doPost(req, resp);
    }

    @Override
    public void destroy() {

    }
}