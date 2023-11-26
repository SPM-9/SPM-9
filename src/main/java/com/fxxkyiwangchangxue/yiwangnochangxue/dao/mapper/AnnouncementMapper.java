package com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper;

import com.fxxkyiwangchangxue.yiwangnochangxue.entity.Announcement;

import java.util.List;

public interface AnnouncementMapper {
    List<Announcement> SelectAll();

    Announcement SelectById(int id);

}
