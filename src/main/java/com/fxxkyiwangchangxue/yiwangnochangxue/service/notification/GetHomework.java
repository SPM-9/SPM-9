package com.fxxkyiwangchangxue.yiwangnochangxue.service.notification;

import com.fxxkyiwangchangxue.yiwangnochangxue.dao.SqlSessionFactoryUtils;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.HomeworkMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.StudyTaskMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.Homework;
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

@WebServlet(name = "GetHomework", value = "/GetHomework")
public class GetHomework extends HttpServlet {
    HomeworkMapper homeworkMapper;
    StudyTaskMapper studyTaskMapper;

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

        homeworkMapper = sqlSession.getMapper(HomeworkMapper.class);
        studyTaskMapper = sqlSession.getMapper(StudyTaskMapper.class);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

        if (operation.equals("getLast")) {
            if (refreshCnt == null)
                return;
            int lastIndex = 0;
            int refreshCount = Integer.parseInt(refreshCnt);

            List<Homework> homeworks = new ArrayList<>(refreshCount + 5);
            for (int i = 0; i < refreshCount; i++) {
                Homework homework;
                if (i == 0)
                    homework = homeworkMapper.selectLastIndex();
                else
                    homework = homeworkMapper.selectPreviousIndex(lastIndex);
                if (homework == null)// 如果返回null，则说明没有更多了
                    break;
                homework = getHomeworkInfo(homework);
                homeworks.add(homework);
                lastIndex = homework.getHomeworkId();
            }
            String json = gson.toJson(homeworks);// gson转换时会自动忽略null值
            pw.print(json);
        } else if (operation.equals("getPrevious")) {
            if (lastIdx == null || refreshCnt == null)
                return;
            int lastIndex = Integer.parseInt(lastIdx);
            int refreshCount = Integer.parseInt(refreshCnt);

            List<Homework> homeworks =new ArrayList<>(refreshCount+5);
            for(int i=0;i<refreshCount;i++){
                Homework homework=homeworkMapper.selectPreviousIndex(lastIndex);
                if(homework == null) // 如果返回null，则说明没有更多了
                    break;
                homework=getHomeworkInfo(homework);
                homeworks.add(homework);
                lastIndex=homework.getHomeworkId();
            }

            String json = gson.toJson(homeworks); // gson转换时会自动忽略null值
            pw.print(json);
        } else if (operation.equals("getAll")) {
            List<Homework> homeworks = homeworkMapper.selectAll();
            String json = gson.toJson(homeworks);
            pw.print(json);
        }
        sqlSession.close();

    }

    private Homework getHomeworkInfo(Homework homework) {
        int homeworkId = homework.getHomeworkId();
        StudyTask studyTask = studyTaskMapper.SelectById(homeworkId);
        homework.setTitle("学习任务发布");
        String body = "课程【项目管理与过程改进】发布学习任务 " + studyTask.getTitle() + "，请查收";
        homework.setBody(body);
        homework.setUploadTime(studyTask.getStartTime());
        return homework;
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
