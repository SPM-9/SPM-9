package com.fxxkyiwangchangxue.yiwangnochangxue.service.board;

import com.fxxkyiwangchangxue.yiwangnochangxue.dao.SqlSessionFactoryUtils;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.CommentMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.Comment;
import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "PostComment", value = "/PostComment")
public class PostComment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = response.getWriter();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");

        Comment comment = new Comment(name, email, subject, content);

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
        Gson gson = new Gson();

        String message;
        try {
            commentMapper.insertComment(comment);
            message = "评论成功";
            String messageJson = gson.toJson(message);
            pw.write(messageJson);
        } catch (SQLException e) {
            message = "评论失败";
            String messageJson = gson.toJson(message);
            pw.write(messageJson);
        }
        sqlSession.close();
    }
}
