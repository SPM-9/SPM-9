package com.fxxkyiwangchangxue.yiwangnochangxue.service.notification;

import com.fxxkyiwangchangxue.yiwangnochangxue.dao.SqlSessionFactoryUtils;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.StudyTaskMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.StudyTask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "GetExam", value = "/GetExam")
public class GetExam extends HttpServlet {

    @Override
    public void init() {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = resp.getWriter();

        String operation = req.getParameter("operation");
        String lastIdx = req.getParameter("lastIndex");
        String refreshCnt = req.getParameter("refreshCount");
        if (operation == null)
            return;
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();

        try (sqlSession) {
            StudyTaskMapper studyTaskMapper = sqlSession.getMapper(StudyTaskMapper.class);
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            if (operation.equals("getLast")) {
                if (refreshCnt == null)
                    return;
                int lastIndex = 0;
                int refreshCount = Integer.parseInt(refreshCnt);

                List<StudyTask> homeworks = new ArrayList<>(refreshCount + 5);
                for (int i = 0; i < refreshCount; i++) {
                    StudyTask studyTaskHomework;
                    if (i == 0)
                        studyTaskHomework = studyTaskMapper.selectLatestExam();
                    else
                        studyTaskHomework = studyTaskMapper.selectPreviousExam(lastIndex);
                    if (studyTaskHomework == null)// 如果返回null，则说明没有更多了
                        break;
                    homeworks.add(studyTaskHomework);
                    lastIndex = studyTaskHomework.getTaskId();
                }
                String json = gson.toJson(homeworks);// gson转换时会自动忽略null值
                pw.print(json);
            } else if (operation.equals("getPrevious")) {
                if (lastIdx == null || refreshCnt == null)
                    return;
                int lastIndex = Integer.parseInt(lastIdx);
                int refreshCount = Integer.parseInt(refreshCnt);

                List<StudyTask> homeworks = new ArrayList<>(refreshCount + 5);
                for (int i = 0; i < refreshCount; i++) {
                    StudyTask studyTaskHomework = studyTaskMapper.selectPreviousExam(lastIndex);
                    if (studyTaskHomework == null) // 如果返回null，则说明没有更多了
                        break;
                    homeworks.add(studyTaskHomework);
                    lastIndex = studyTaskHomework.getTaskId();
                }

                String json = gson.toJson(homeworks); // gson转换时会自动忽略null值
                pw.print(json);
            } else if (operation.equals("getAll")) {
//            List<Homework> homeworks = homeworkMapper.selectAll();
//            String json = gson.toJson(homeworks);
//            pw.print(json);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        doPost(req, resp);
    }

    @Override
    public void destroy() {

    }
}
