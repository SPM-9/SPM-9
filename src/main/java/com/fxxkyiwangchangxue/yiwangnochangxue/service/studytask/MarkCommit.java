package com.fxxkyiwangchangxue.yiwangnochangxue.service.studytask;

import java.io.*;

import com.fxxkyiwangchangxue.yiwangnochangxue.dao.SqlSessionFactoryUtils;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.CommitMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.ibatis.session.SqlSession;

@WebServlet(name = "MarkCommit", value = "/MarkCommit")
public class MarkCommit extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        String resultStr = req.getParameter("result");
        String commitIdStr = req.getParameter("commitId");
        if (resultStr == null || commitIdStr == null) {
            resp.setStatus(500);
            return;
        }
        int result = Integer.parseInt(resultStr);
        int commitId = Integer.parseInt(commitIdStr);

        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        CommitMapper commitMapper = sqlSession.getMapper(CommitMapper.class);
        try (sqlSession) {
            commitMapper.updateScoreByCommitId(commitId, result);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            resp.setStatus(500);
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