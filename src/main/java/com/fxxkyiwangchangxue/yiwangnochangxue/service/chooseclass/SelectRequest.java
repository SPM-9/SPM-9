package com.fxxkyiwangchangxue.yiwangnochangxue.service.chooseclass;

import com.fxxkyiwangchangxue.yiwangnochangxue.dao.SqlSessionFactoryUtils;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.SelectCourseRequestMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import java.io.IOException;
@WebServlet(name = "SelectRequest", value = "/selectRequest")
public class SelectRequest extends HttpServlet {
    public void init(){

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        String uidString = req.getParameter("uid");
        if (uidString == null)
            return;

        int uid = Integer.parseInt(uidString);

        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        SelectCourseRequestMapper courseRequestMapper= sqlSession.getMapper(SelectCourseRequestMapper.class);

        try (sqlSession) {
            courseRequestMapper.InsertByUID(uid);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            resp.setStatus(500);
            return;
        }

        resp.setStatus(200);
    }


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doPost(req, resp);
    }

    @Override
    public void destroy() {

    }
}
