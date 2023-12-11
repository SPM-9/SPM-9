package com.fxxkyiwangchangxue.yiwangnochangxue.dao;

import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.SelectCourseRequestMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.UserMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.User;
import com.google.gson.Gson;
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
        String userName = "ZhijiangDiana";
        String password = "114514";

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User loginUser = userMapper.selectByLogin(userName, password);
        if (loginUser == null) {
            System.out.print("false");
        } else {
            Gson gson = new Gson();
            String json = gson.toJson(loginUser);
            System.out.print(json);
        }

        sqlSession.close();
    }

    @Test
    public void testRegister() throws IOException {
        String userName = "DaoGe";
        String password = "114514";
        String email = "114514@qq.com";
        String nickName = "刀哥";

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = new User(userName, password, nickName, email);
        userMapper.insertByRegister(user);
        // insert和update语句使用后必须commit！！！
        sqlSession.commit();

        System.out.println(user.getUid());

        sqlSession.close();
    }

    @Test
    public void checkclass() throws IOException {
        int uid = 1;

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectByUID(uid);
        sqlSession.close();
        System.out.println(user.isChosenCourse());
    }
    @Test
    public void insert() throws IOException {
        int uid=3;

        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();

        SelectCourseRequestMapper courseRequestMapper= sqlSession.getMapper(SelectCourseRequestMapper.class);
        courseRequestMapper.InsertByUID(uid);
        sqlSession.commit();
        sqlSession.close();

        System.out.println(1);
    }
    @Test
    public void permit()throws IOException {
        int id = 1;
        int uid = 3;
        boolean accept = true;

        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();

        SelectCourseRequestMapper courseRequestMapper= sqlSession.getMapper(SelectCourseRequestMapper.class);
        courseRequestMapper.updateByTeacherId(id,uid,accept);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void modify()throws IOException {


        int uid = 1;
        boolean accept = true;

        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();

        UserMapper userMapper= sqlSession.getMapper(UserMapper.class);
        userMapper.updateByUid(uid,accept);
        sqlSession.commit();
        sqlSession.close();

    }
}
