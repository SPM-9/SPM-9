package com.fxxkyiwangchangxue.yiwangnochangxue.dao;

import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.AnnouncementMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.NotificationMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.StudyTaskMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.Announcement;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.Notification;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.StudyTask;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.List;

public class NotificationTest {
    @Test
    public void testSelectAll() {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        NotificationMapper notificationMapper = sqlSession.getMapper(NotificationMapper.class);
        AnnouncementMapper announcementMapper = sqlSession.getMapper(AnnouncementMapper.class);
        StudyTaskMapper studyTaskMapper = sqlSession.getMapper(StudyTaskMapper.class);

        // [Notification{notifId=1, fori_taskId=null, fori_annId=1, notifType=1, title='null', body='null', uploadTime=null},
        // Notification{notifId=2, fori_taskId=null, fori_annId=2, notifType=1, title='null', body='null', uploadTime=null},
        // Notification{notifId=4, fori_taskId=null, fori_annId=3, notifType=1, title='null', body='null', uploadTime=null}]
        List<Notification> notifications = notificationMapper.selectAll();
//        System.out.println(notifications);
        for (Notification n : notifications) {
            int notifType = n.getNotifType();
            if (notifType == Notification.TASK) {
                int fori_taskId = n.getFori_taskId();
                StudyTask studyTask = studyTaskMapper.SelectById(fori_taskId);
                n.setTitle(studyTask.getTitle());
                n.setBody(studyTask.getBody());
                n.setUploadTime(studyTask.getStartTime());
            } else if (notifType == Notification.ANNOUNCEMENT) {
                int fori_annId = n.getFori_annId();
                Announcement announcement = announcementMapper.SelectById(fori_annId);
                n.setTitle(announcement.getTitle());
                n.setBody(announcement.getBody());
                n.setUploadTime(announcement.getUploadTime());
            }
            System.out.println(n);
        }
        System.out.println(notifications);


        sqlSession.close();
    }
}
