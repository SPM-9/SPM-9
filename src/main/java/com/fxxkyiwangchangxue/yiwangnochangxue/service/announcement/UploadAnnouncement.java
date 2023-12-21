package com.fxxkyiwangchangxue.yiwangnochangxue.service.announcement;

import java.io.*;
import java.util.Date;

import com.fxxkyiwangchangxue.yiwangnochangxue.dao.SqlSessionFactoryUtils;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.AnnouncementMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.NotificationMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.Announcement;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.ibatis.session.SqlSession;

@WebServlet(name = "UploadAnnouncement", value = "/UploadAnnouncement")
public class UploadAnnouncement extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        String teacherIdString = req.getParameter("teacherId");
        String title = req.getParameter("title");
        String body = req.getParameter("body");
        if (teacherIdString == null || title == null || body == null) {
            resp.setStatus(500);
            return;
        }
        int teacherId = Integer.parseInt(teacherIdString);

        Announcement announcement = new Announcement();
        announcement.setFori_teacherId(teacherId);
        announcement.setTitle(title);
        announcement.setBody(body);
        Date date = new Date(System.currentTimeMillis());
        announcement.setUploadTime(date);

        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        AnnouncementMapper announcementMapper = sqlSession.getMapper(AnnouncementMapper.class);
        NotificationMapper notificationMapper = sqlSession.getMapper(NotificationMapper.class);

        try (sqlSession) {
            announcementMapper.insertAnnouncement(announcement);
            int taskId = announcement.getAnnId();
            notificationMapper.insertAnnouncementNotif(taskId);
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