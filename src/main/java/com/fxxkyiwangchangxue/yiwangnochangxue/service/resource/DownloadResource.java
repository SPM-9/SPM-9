package com.fxxkyiwangchangxue.yiwangnochangxue.service.resource;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.fxxkyiwangchangxue.yiwangnochangxue.dao.SqlSessionFactoryUtils;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.ResourceMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.StudyTaskMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.Resource;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.StudyTask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.session.SqlSession;

@WebServlet(name = "DownloadResource", value = "/DownloadResource")
@MultipartConfig
public class DownloadResource extends HttpServlet {
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
        PrintWriter pw = resp.getWriter();
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        ResourceMapper resourceMapper=sqlSession.getMapper(ResourceMapper.class);

        List<Resource> resource;
        try (sqlSession) {
            resource = resourceMapper.getResource();
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
            return;
        }

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        String json = gson.toJson(resource);
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