package com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper;

import com.fxxkyiwangchangxue.yiwangnochangxue.entity.UserSigns;
import com.google.gson.JsonArray;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface UserSignMapper {
    List<UserSigns> selectAllByTime(Date now);

    UserSigns selectById(@Param("id") Integer id);


    void updateSignUId(@Param("id") Integer id, @Param("signUId") String signUId);

    List<UserSigns> selectAll();


}
