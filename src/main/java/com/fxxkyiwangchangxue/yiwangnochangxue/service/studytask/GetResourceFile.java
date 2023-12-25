package com.fxxkyiwangchangxue.yiwangnochangxue.service.studytask;

import com.fxxkyiwangchangxue.yiwangnochangxue.dao.SqlSessionFactoryUtils;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.CommitMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.ResourceMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.Commit;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.Resource;
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

@WebServlet(name = "GetResourceFile", value = "/GetResourceFile")
public class GetResourceFile extends HttpServlet {

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

        String taskIdString = req.getParameter("resourceId");
        if (taskIdString == null)
            return;
        int taskId = Integer.parseInt(taskIdString);

        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        ResourceMapper resourceMapper = sqlSession.getMapper(ResourceMapper.class);

        Resource resource;
        try (sqlSession) {
            resource = resourceMapper.selectResourceFile(taskId);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
            return;
        }
        // 没有文件则返回404
        if (resource.getFileName() == null || resource.getFile() == null) {
            resp.setStatus(404);
            return;
        }
        // 找到文件则发送
        String fileName = resource.getFileName();
        fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8); // 处理中文乱码

        // 发送文件
        resp.setHeader("Content-Disposition","attachment;filename=" + fileName);
        InputStream fileIn = new ByteArrayInputStream(resource.getFile());
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