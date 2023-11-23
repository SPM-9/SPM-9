package com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper;

import com.fxxkyiwangchangxue.yiwangnochangxue.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /**
     * 查询所有用户
     * @return List
     */
    List<User> selectAll();
    User selectByUID(int uid);
    User selectByLogin(@Param("userName") String userName,
                       @Param("password") String password);
}
