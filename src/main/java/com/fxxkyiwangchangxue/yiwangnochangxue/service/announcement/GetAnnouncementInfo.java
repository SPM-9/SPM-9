package com.fxxkyiwangchangxue.yiwangnochangxue.service.announcement;

import java.io.*;

import com.fxxkyiwangchangxue.yiwangnochangxue.dao.SqlSessionFactoryUtils;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.AnnouncementMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.Announcement;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.ibatis.session.SqlSession;

@WebServlet(name = "GetAnnouncementInfo", value = "/GetAnnouncementInfo")
public class GetAnnouncementInfo extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        String annIdString = req.getParameter("annId");
        if (annIdString == null)
            return;
        int annId = Integer.parseInt(annIdString);

        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        AnnouncementMapper announcementMapper = sqlSession.getMapper(AnnouncementMapper.class);

        Announcement announcement = announcementMapper.SelectById(annId);
        sqlSession.close();

        if (announcement == null) {
            resp.setStatus(404);
            return;
        }
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        String json = gson.toJson(announcement, Announcement.class);
        pw.print(json);
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