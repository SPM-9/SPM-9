package com.fxxkyiwangchangxue.yiwangnochangxue.service.chooseclass;

import com.fxxkyiwangchangxue.yiwangnochangxue.dao.SqlSessionFactoryUtils;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.UserMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import java.io.IOException;

@WebServlet(name = "CheckClass", value = "/checkClass")
public class CheckClass extends HttpServlet {
    public void init(){

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("受到请求");
        resp.setContentType("text/html; charset = UTF-8");
        String uidString = req.getParameter("uid");
        if (uidString == null)
            return;

        int uid = Integer.parseInt(uidString);

        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user;
        try (sqlSession) {
            user = userMapper.selectByUID(uid);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
            return;
        }
        if (!user.isChosenCourse()) {
            resp.setStatus(404);
        }else {
            resp.setStatus(200);
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
