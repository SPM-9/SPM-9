package com.fxxkyiwangchangxue.yiwangnochangxue.dao;

import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.AnnouncementMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.NotificationMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.Announcement;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.Notification;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AnnouncementTest {
    @Test
    public void testSelectAll() {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        AnnouncementMapper announcementMapper = sqlSession.getMapper(AnnouncementMapper.class);

        List<Announcement> announcements = announcementMapper.SelectAll();
        System.out.println(announcements);

        sqlSession.close();
    }
}
