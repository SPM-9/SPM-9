package com.fxxkyiwangchangxue.yiwangnochangxue.dao;

import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.UserMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisDemo2 {
    public static void main(String[] args) throws IOException {
        // 1.加载mybatis配置文件，获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.获取SqlSession对象，用它执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

//        // 3.执行sql
//        List<User> users = sqlSession.selectList("User.selectAll");
        // 3.1获取UserMapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.selectAll();

        System.out.println(users);

        // 4.释放资源
        sqlSession.close();
    }
}
