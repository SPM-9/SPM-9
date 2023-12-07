package com.fxxkyiwangchangxue.yiwangnochangxue.service.resource;

import java.io.*;

import com.fxxkyiwangchangxue.yiwangnochangxue.dao.SqlSessionFactoryUtils;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.ResourceMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.session.SqlSession;

@WebServlet(name = "UploadResource", value = "/UploadResource")
@MultipartConfig
public class UploadResource extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        String title = req.getParameter("title");
        String body = req.getParameter("body");
        Part filePart = req.getPart("file");
        if (title == null || body == null || filePart == null) {
            resp.setStatus(500);
            return;
        }
        if (body.isEmpty())
            body = null;

        String fileName = filePart.getSubmittedFileName();
        InputStream is = filePart.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        IOUtils.copy(bis, baos);
        byte[] file = baos.toByteArray();

        Resource resource = new Resource();
        resource.setTitle(title);
        resource.setBody(body);
        resource.setFileName(fileName);
        resource.setFile(file);

        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        ResourceMapper resourceMapper = sqlSession.getMapper(ResourceMapper.class);
        resourceMapper.insertResource(resource);
        sqlSession.commit();
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