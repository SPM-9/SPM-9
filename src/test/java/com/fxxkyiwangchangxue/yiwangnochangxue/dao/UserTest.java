package com.fxxkyiwangchangxue.yiwangnochangxue.dao;

import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.UserMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserTest {
    @Test
    public void testSelectAll() throws IOException {
        // 1.加载mybatis配置文件，获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.获取SqlSession对象，用它执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3.1获取UserMapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.selectAll();

        System.out.println(users);

        // 4.释放资源
        sqlSession.close();
    }

    @Test
    public void testSelectByUID() throws IOException {
        // 1.加载mybatis配置文件，获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2.获取SqlSession对象，用它执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3.1获取UserMapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user1 = userMapper.selectByUID(1);
        User user2 = userMapper.selectByUID(2);
        User user3 = userMapper.selectByUID(114514);

        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);

        // 4.释放资源
        sqlSession.close();
    }

    @Test
    public void testLogin() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User loginSuccess = userMapper.selectByLogin("ZhijiangDiana", "114514");
        User userNameFail = userMapper.selectByLogin("114514", "114514");
        User passwordFail = userMapper.selectByLogin("ZhijiangDiana", "1919810");

        System.out.println(loginSuccess);
        System.out.println(userNameFail);
        System.out.println(passwordFail);

        sqlSession.close();
    }
}
