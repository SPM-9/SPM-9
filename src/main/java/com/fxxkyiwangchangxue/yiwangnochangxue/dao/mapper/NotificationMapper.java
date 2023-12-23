package com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper;

import com.fxxkyiwangchangxue.yiwangnochangxue.entity.Notification;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.StudyTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NotificationMapper {
    List<Notification> selectAll();

    Notification selectLastIndex();

    Notification selectPreviousIndex(int thisIndex);

    void insertStudyTaskNotif(@Param("taskId") int taskId);

    void insertAnnouncementNotif(@Param("annId") int annId);
}
