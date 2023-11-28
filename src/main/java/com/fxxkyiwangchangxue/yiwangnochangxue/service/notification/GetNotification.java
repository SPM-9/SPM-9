package com.fxxkyiwangchangxue.yiwangnochangxue.service.notification;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.fxxkyiwangchangxue.yiwangnochangxue.dao.SqlSessionFactoryUtils;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.AnnouncementMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.NotificationMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.StudyTaskMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.Announcement;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.Notification;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.StudyTask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.ibatis.session.SqlSession;

@WebServlet(name = "GetNotification", value = "/GetNotification")
public class GetNotification extends HttpServlet {

    NotificationMapper notificationMapper;
    AnnouncementMapper announcementMapper;
    StudyTaskMapper studyTaskMapper;
    @Override
    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        String operation = req.getParameter("operation");
        String lastIdx = req.getParameter("lastIndex");
        String refreshCnt = req.getParameter("refreshCount");
        if (operation == null)
            return;
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();

        notificationMapper = sqlSession.getMapper(NotificationMapper.class);
        announcementMapper = sqlSession.getMapper(AnnouncementMapper.class);
        studyTaskMapper = sqlSession.getMapper(StudyTaskMapper.class);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

        if (operation.equals("getLast")) {
            if (refreshCnt == null)
                return;
            int lastIndex = 0;
            int refreshCount = Integer.parseInt(refreshCnt);

            List<Notification> respNotifs = new ArrayList<>(refreshCount + 5);
            for (int i=0; i<refreshCount; i++) {
                Notification notification;
                if (i == 0)
                    notification = notificationMapper.selectLastIndex();
                else
                    notification = notificationMapper.selectPreviousIndex(lastIndex);
                if (notification == null) // 如果返回null，则说明没有更多了
                    break;
                notification = getNotifInfo(notification);
                respNotifs.add(notification);
                lastIndex = notification.getNotifId();
            }

            String json = gson.toJson(respNotifs);
            pw.print(json);
        } else if (operation.equals("getPrevious")) {
            if (lastIdx == null || refreshCnt == null)
                return;
            int lastIndex = Integer.parseInt(lastIdx);
            int refreshCount = Integer.parseInt(refreshCnt);

            List<Notification> respNotifs = new ArrayList<>(refreshCount + 5);
            for (int i=0; i<refreshCount; i++) {
                Notification notification = notificationMapper.selectPreviousIndex(lastIndex);
                if (notification == null) // 如果返回null，则说明没有更多了
                    break;
                notification = getNotifInfo(notification);
                respNotifs.add(notification);
                lastIndex = notification.getNotifId();
            }

            String json = gson.toJson(respNotifs); // gson转换时会自动忽略null值
            pw.print(json);
        } else if (operation.equals("getAll")) {
            List<Notification> notifications = notificationMapper.selectAll();
            String json = gson.toJson(notifications);
            pw.print(json);
        }

        sqlSession.close();
    }

    private Notification getNotifInfo(Notification notification) {
        int notifType = notification.getNotifType();
        if (notifType == Notification.TASK) {
            int fori_taskId = notification.getFori_taskId();
            StudyTask studyTask = studyTaskMapper.SelectById(fori_taskId);
            notification.setTitle("学习任务发布");
            String body = "课程【项目管理与过程改进】发布学习任务 " + studyTask.getTitle() + "，请查收";
            notification.setBody(body);
            notification.setUploadTime(studyTask.getStartTime());
        } else if (notifType == Notification.ANNOUNCEMENT) {
            int fori_annId = notification.getFori_annId();
            Announcement announcement = announcementMapper.SelectById(fori_annId);
            notification.setTitle("公告发布");
            String body = "课程【项目管理与过程改进】发布公告 " + announcement.getTitle() + "，请查收";
            notification.setBody(body);
            notification.setUploadTime(announcement.getUploadTime());
        }
        return notification;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        doPost(req, resp);
    }

    @Override
    public void destroy() {

    }
}