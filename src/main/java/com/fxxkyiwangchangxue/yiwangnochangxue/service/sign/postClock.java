package com.fxxkyiwangchangxue.yiwangnochangxue.service.sign;

import com.fxxkyiwangchangxue.yiwangnochangxue.dao.SqlSessionFactoryUtils;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.UserSignMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.UserSigns;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;

@WebServlet(name = "postClock", value = "/postClock")
public class postClock extends HttpServlet {
    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; chatset=UTF-8");
        PrintWriter printWriter= response.getWriter();
        Integer postResult = null;//-1表示失败，不为-1为分配的签到号
        //获取签到发布信息
        BufferedReader streamReader = new BufferedReader( new InputStreamReader(request.getInputStream(), "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null) {
            sb.append(inputStr);
        }
        String signInformJson = sb.toString();
        UserSigns signInform;
        if (signInformJson==null){
            return;
        }
        signInform = gson.fromJson(signInformJson, UserSigns.class);

        //是否为空
            //为空返回发布失败
        if (signInform==null){
            postResult=-1;
            String postResultJson = gson.toJson(postResult);
            printWriter.write(postResultJson);
            return;
        }
        //判断发起时间是否在当前时间后
        if(signInform.getEndTime().after(new Date())&&signInform.getEndTime().after(signInform.getStartTime())){
            //更新数据库
            try {
                Integer signId = insertSign(signInform);
                postResult=signId;
            } catch (SQLException e) {
                postResult=-1;
            }
        }else {
            postResult=-1;
        }
        String postResultJson = gson.toJson(postResult);
        printWriter.write(postResultJson);

    }

    private Integer insertSign(UserSigns signInform) throws SQLException {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserSignMapper userSignMapper = sqlSession.getMapper(UserSignMapper.class);

        try (sqlSession) {
            userSignMapper.insertNewSign(signInform);
            return signInform.getSignId();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            throw new SQLException("SB数据库");
        }
    }
}
