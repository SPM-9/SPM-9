package com.fxxkyiwangchangxue.yiwangnochangxue.service.studytask;

import java.io.*;
import java.util.List;

import com.fxxkyiwangchangxue.yiwangnochangxue.dao.SqlSessionFactoryUtils;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.CommitMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.Commit;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.ibatis.session.SqlSession;

@WebServlet(name = "GetStudyTaskCommit", value = "/GetStudyTaskCommit")
public class GetStudyTaskCommit extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        String studyTaskIdStr = req.getParameter("studyTaskId");
        if (studyTaskIdStr == null) {
            resp.setStatus(500);
            return;
        }
        int studyTaskId = Integer.parseInt(studyTaskIdStr);

        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        CommitMapper commitMapper = sqlSession.getMapper(CommitMapper.class);

        List<Commit> res = null;
        try (sqlSession) {
            res = commitMapper.selectByStudyTaskId(studyTaskId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (res == null) {
            resp.setStatus(500);
            return;
        }
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        String json = gson.toJson(res);// gson转换时会自动忽略null值
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