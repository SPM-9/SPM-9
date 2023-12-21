package com.fxxkyiwangchangxue.yiwangnochangxue.service.studytask;

import java.io.*;

import com.fxxkyiwangchangxue.yiwangnochangxue.dao.SqlSessionFactoryUtils;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.CommitMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.Commit;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.ibatis.session.SqlSession;

@WebServlet(name = "GetIsCommit", value = "/GetIsCommit")
public class GetIsCommit extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        String userIdString = req.getParameter("userId");
        String taskIdString = req.getParameter("taskId");
        if (userIdString == null || taskIdString == null)
            return;

        int userId = Integer.parseInt(userIdString);
        int taskId = Integer.parseInt(taskIdString);

        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        CommitMapper commitMapper = sqlSession.getMapper(CommitMapper.class);

        Commit commit = null;
        try (sqlSession) {
            commit = commitMapper.SelectByTaskIdUId(taskId, userId);
            if (commit == null)
                return;
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
            return;
        }

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        String json = gson.toJson(commit);
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