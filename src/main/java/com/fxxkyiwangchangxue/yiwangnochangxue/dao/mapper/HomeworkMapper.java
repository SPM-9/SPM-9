package com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper;

import com.fxxkyiwangchangxue.yiwangnochangxue.entity.Homework;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HomeworkMapper {
    List<Homework> selectAll();

    Homework selectLastIndex();

    Homework selectPreviousIndex(int thisIndex);

    Homework SelectById(int id);
    Homework SelectFileById(int id);
    Long SelectFileSizeById(int id);
}
