package com.fxxkyiwangchangxue.yiwangnochangxue.service.classpermission;

import com.fxxkyiwangchangxue.yiwangnochangxue.dao.SqlSessionFactoryUtils;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.SelectCourseRequestMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.UserMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import java.io.IOException;
@WebServlet(name = "ModifyChooseClass", value = "/modifychooseclass")
public class modifychooseclass extends HttpServlet {
    public void init(){

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        String studentuid = req.getParameter("uid");
        String isAccept = req.getParameter("ischosencourse");
        if (isAccept == null||studentuid == null)
            return;

        int uid = Integer.parseInt(studentuid);
        boolean accept = Boolean.parseBoolean(isAccept);

        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        UserMapper userMapper= sqlSession.getMapper(UserMapper.class);

        try (sqlSession) {
            userMapper.updateByUid(uid,accept);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            resp.setStatus(500);
            return;
        }
        if(accept){
            resp.setStatus(200);
        }else {
            resp.setStatus(404);
        }

    }


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doPost(req, resp);
    }

    @Override
    public void destroy() {

    }
}