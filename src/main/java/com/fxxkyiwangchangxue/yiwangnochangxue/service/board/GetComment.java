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
import java.util.List;

@WebServlet(name = "GetComment", value = "/GetComment")
public class GetComment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = response.getWriter();

        //获取最新count个评论
        String count0 = request.getParameter("count");
        Integer count = Integer.parseInt(count0);
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
        Gson gson = new Gson();

        String message;
        try {
            List<Comment> comments = commentMapper.selectAllByCount(count);
            String commentsJson = gson.toJson(comments);
            pw.write(commentsJson);
            response.setStatus(222);
        } catch (SQLException e) {
            response.setStatus(555);
        }
        sqlSession.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
