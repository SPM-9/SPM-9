package com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper;

import com.fxxkyiwangchangxue.yiwangnochangxue.entity.StudyTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudyTaskMapper {
    List<StudyTask> SelectAll();

    StudyTask SelectById(int id);

    StudyTask SelectFileById(int id);

    Long SelectFileSizeById(int id);

    void insertStudyTask(StudyTask studyTask);

    StudyTask selectLatestHomework();

    StudyTask selectLatestExam();

    StudyTask selectPreviousHomework(@Param("thisIndex") int index);

    StudyTask selectPreviousExam(@Param("thisIndex") int index);

    StudyTask selectLatestNoMarkHomework();

    StudyTask selectLatestNoMarkExam();

    StudyTask selectPreviousNoMarkHomework(@Param("thisIndex") int index);

    StudyTask selectPreviousNoMarkExam(@Param("thisIndex") int index);

    List<StudyTask> selectTodoStudyTask(@Param("userId") int userId);
}
