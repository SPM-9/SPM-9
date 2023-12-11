package com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper;
import org.apache.ibatis.annotations.Param;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.Announcement;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.SelectCourseRequest;

public interface SelectCourseRequestMapper {
    SelectCourseRequest SelectByUId(int uid);

    void InsertByUID(int uid);

    void updateByTeacherId(@Param("id")int id,@Param("uid") int uid,@Param("accept") boolean accept);
}
