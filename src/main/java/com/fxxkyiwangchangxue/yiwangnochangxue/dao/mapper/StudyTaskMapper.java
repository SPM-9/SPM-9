package com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper;

import com.fxxkyiwangchangxue.yiwangnochangxue.entity.Announcement;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.StudyTask;

import java.util.List;

public interface StudyTaskMapper {
    List<StudyTask> SelectAll();

    StudyTask SelectById(int id);
}
