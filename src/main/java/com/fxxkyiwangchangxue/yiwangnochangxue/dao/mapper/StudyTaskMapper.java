package com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper;

import com.fxxkyiwangchangxue.yiwangnochangxue.entity.Announcement;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.StudyTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudyTaskMapper {
    List<StudyTask> SelectAll();

    StudyTask SelectById(int id);

    StudyTask SelectFileById(int id);

    Long SelectFileSizeById(int id);

    void insertStudyTask(StudyTask studyTask);
}
