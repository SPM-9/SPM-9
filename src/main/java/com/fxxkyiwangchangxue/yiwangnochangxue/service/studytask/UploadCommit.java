package com.fxxkyiwangchangxue.yiwangnochangxue.service.studytask;

import java.io.*;

import com.fxxkyiwangchangxue.yiwangnochangxue.dao.SqlSessionFactoryUtils;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.CommitMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.session.SqlSession;

@WebServlet(name = "UploadCommit", value = "/UploadCommit")
@MultipartConfig
public class UploadCommit extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        String userIdString = req.getParameter("userId");
        String taskIdString = req.getParameter("taskId");
        String body = req.getParameter("body");
        Part filePart = req.getPart("file");
        if (userIdString == null || taskIdString == null || body == null) {
            resp.setStatus(500);
            return;
        }

        int userId = Integer.parseInt(userIdString);
        int taskId = Integer.parseInt(taskIdString);


        String fileName = null;
        byte[] file = null;
        if (filePart != null) {
            fileName = filePart.getSubmittedFileName();
            InputStream is = filePart.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try (bis; baos){
                IOUtils.copy(bis, baos);
                file = baos.toByteArray();
            } catch (Exception e) {
                e.printStackTrace();
                resp.setStatus(500);
                return;
            }
        }

        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        CommitMapper commitMapper = sqlSession.getMapper(CommitMapper.class);

        try (sqlSession) {
            commitMapper.InsertCommit(userId, taskId, body, fileName, file);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
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