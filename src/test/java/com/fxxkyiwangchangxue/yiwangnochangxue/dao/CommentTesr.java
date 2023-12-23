package com.fxxkyiwangchangxue.yiwangnochangxue.dao;

import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.CommentMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.UserSignMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.Comment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.SQLException;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CommentTesr {
    static CommentMapper commentMapper;
    static SqlSession sqlSession;
    static Gson gson;

    @BeforeAll
    public void getMapper(){
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        sqlSession = sqlSessionFactory.openSession(true);
        commentMapper = sqlSession.getMapper(CommentMapper.class);
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }
    @Test
    void testInsertComment(){
        Comment ss = new Comment("ss", "2s", "3s", "4s");
//        commentMapper.insertComment(ss);
    }
    @Test
    void testSelectByCount() throws SQLException {
        List<Comment> comments = commentMapper.selectAllByCount(2);
        System.out.println(comments);
    }
}
