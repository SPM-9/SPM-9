package com.fxxkyiwangchangxue.yiwangnochangxue.service.classpermission;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.SqlSessionFactoryUtils;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.SelectCourseRequestMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import java.io.IOException;
@WebServlet(name = "ClassPermission", value = "/classpermission")
public class classpermission extends HttpServlet {
    public void init(){

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        String TeacherId = req.getParameter("TeacherId");
        String studentuid = req.getParameter("uid");
        String isAccept = req.getParameter("isAccept");
        if (TeacherId == null||isAccept == null||studentuid == null)
            return;

        int id = Integer.parseInt(TeacherId);
        int uid = Integer.parseInt(studentuid);
        boolean accept = Boolean.parseBoolean(isAccept);

        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();

        SelectCourseRequestMapper courseRequestMapper= sqlSession.getMapper(SelectCourseRequestMapper.class);
        courseRequestMapper.updateByTeacherId(id,uid,accept);
        sqlSession.commit();
        sqlSession.close();
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

