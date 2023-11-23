package com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper;

import com.fxxkyiwangchangxue.yiwangnochangxue.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /**
     * 查询所有用户
     * @return List 返回User列表
     */
    List<User> selectAll();

    /**
     * 按uid查询用户
     * @param uid 用户的uid
     * @return User 返回User对象，uid不存在则返回null
     */
    User selectByUID(int uid);

    /**
     * 按用户名和密码查询用户
     * @param userName 用户名
     * @param password 密码
     * @return User 返回User对象，登录失败返回null
     */
    User selectByLogin(@Param("userName") String userName,
                       @Param("password") String password);
}
