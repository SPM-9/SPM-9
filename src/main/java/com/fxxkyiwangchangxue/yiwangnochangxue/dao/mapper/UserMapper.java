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

    /**
     * 按用户名查询用户
     * @param userName 用户名
     * @return User 返回User对象，未找到用户则返回null
     */
    User selectByUserName(String userName);

    /**
     * （可以借此返回用户id）使用用户对象注册用户，注册前务必查询用户名是否重复，否则会抛异常！！！
     * @param user 用户对象，用户名、密码、邮箱和昵称不能为空
     */
    void insertByRegister(User user);

    void updateByUid(@Param("uid")int uid,@Param("isaccept")boolean isaccept);
}
