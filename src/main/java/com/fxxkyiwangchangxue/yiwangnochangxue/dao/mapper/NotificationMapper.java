package com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper;

import com.fxxkyiwangchangxue.yiwangnochangxue.entity.Notification;

import java.util.List;

public interface NotificationMapper {
    List<Notification> selectAll();

    Notification selectLastIndex();

    Notification selectPreviousIndex(int thisIndex);
}
