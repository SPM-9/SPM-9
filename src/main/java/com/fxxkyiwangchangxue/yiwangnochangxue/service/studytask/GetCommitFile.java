package com.fxxkyiwangchangxue.yiwangnochangxue.service.studytask;

import com.fxxkyiwangchangxue.yiwangnochangxue.dao.SqlSessionFactoryUtils;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.CommitMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.StudyTaskMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.Commit;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.StudyTask;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "GetCommitFile", value = "/GetCommitFile")
public class GetCommitFile extends HttpServlet {

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

        String taskIdString = req.getParameter("commitId");
        if (taskIdString == null)
            return;
        int taskId = Integer.parseInt(taskIdString);

        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        CommitMapper commitMapper = sqlSession.getMapper(CommitMapper.class);

        Commit commit;
        try (sqlSession) {
            commit = commitMapper.SelectFileById(taskId);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
            return;
        }
        // 没有文件则返回404
        if (commit.getFileName() == null || commit.getFile() == null) {
            resp.setStatus(404);
            return;
        }
        // 找到文件则发送
        String fileName = commit.getFileName();
        fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8); // 处理中文乱码

        // 发送文件
        resp.setHeader("Content-Disposition","attachment;filename=" + fileName);
        InputStream fileIn = new ByteArrayInputStream(commit.getFile());
        ServletOutputStream fileOut = resp.getOutputStream();

        try (sqlSession) {
            IOUtils.copy(fileIn, fileOut);
        } catch (Exception e) {
            e.printStackTrace();
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