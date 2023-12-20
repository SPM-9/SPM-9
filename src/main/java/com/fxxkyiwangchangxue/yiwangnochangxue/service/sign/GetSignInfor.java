package com.fxxkyiwangchangxue.yiwangnochangxue.service.sign;

import com.fxxkyiwangchangxue.yiwangnochangxue.dao.SqlSessionFactoryUtils;
import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.UserSignMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.UserSign;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.UserSigns;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "GetSignInfor", value = "/GetSignInfor")
public class GetSignInfor extends HttpServlet {
    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("GetSignInfor收到了get请求");
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = response.getWriter();
        //userSign = new UserSign(null, 1, new Date(), null);
        //判断请求类型
        String action = request.getParameter("action");
        if(action!=null&&action.equals("getSign")){
            String userSignJson = request.getParameter("userSign");
            UserSign userSign = gson.fromJson(userSignJson, UserSign.class);
            //获取当前时间存在的签到
            List<UserSigns> currentSignsList = obtainCurrentSign();
            System.out.println("currentSignsList"+currentSignsList);
            System.out.println("userSign.getStudentId():"+userSign.getStudentId());

            //获取状态为未签到状态的签到列表（有签到（签过、没签过），无签到）
            List<UserSigns> appSignList = getAppSignList(currentSignsList, userSign.getStudentId());

            System.out.println("appSignList"+appSignList);
            //响应当前需要签到的签到列表
            String appSignListJson = gson.toJson(appSignList);
            System.out.println("appSignListJson:"+appSignListJson);
            pw.write(appSignListJson);
            System.out.println("当前时间："+new Date());
            System.out.println("GetSignInfor获取完当前时间存在的签到");
        }else if(action!=null&&action.equals("submitSign")){
            String userSignJson = request.getParameter("userSign");
            UserSign userSign = gson.fromJson(userSignJson, UserSign.class);
            System.out.println("GetSignInfor开始查询数据库是否超过签到时间");
            String isOvertime;
            //查询数据库是否超过签到时间
            if(isOvertime(userSign)){
                //查询是否已签到
                isOvertime="overtime";
                pw.write(gson.toJson(isOvertime));
                System.out.println("GetSignInfor查询结果：overtime");
                response.setStatus(444);
            }else{
                String sign = getSignById(userSign.getSignId());
                if(judgeStatus(sign,userSign.getStudentId())){
                    isOvertime = "alreadySign";
                    pw.write(gson.toJson(isOvertime));

                }else {
                    String signUId = getSignById(userSign.getSignId());
                    if(judgeStatus(signUId,userSign.getStudentId())){
                        isOvertime = "alreadySign";
                        pw.write(gson.toJson(isOvertime));

                    }else {
                        isOvertime = "ontime";
                        //向数据库中更新数据
                        if(signUpdate(userSign)){
                            pw.write(gson.toJson(isOvertime));
                            System.out.println("GetSignInfor查询结果：ontime/alreadySign");
                            response.setStatus(222);
                        }else {
                            System.out.println("更新错误");
                        }
                    }

                }

            }
        }
    }

    private String getSignById(Integer signId) {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserSignMapper userSignMapper = sqlSession.getMapper(UserSignMapper.class);
        UserSigns userSigns = userSignMapper.selectById(signId);
        String signUIdJson = userSigns.getSignUId();
        sqlSession.close();

        return signUIdJson;
    }

    private boolean signUpdate(UserSign userSign) {

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserSignMapper userSignMapper = sqlSession.getMapper(UserSignMapper.class);
        UserSigns userSigns = userSignMapper.selectById(userSign.getSignId());

        //获取数据库已签到列表
        String signUId = userSigns.getSignUId();
        Type type = new TypeToken<List<UserSign>>() {
        }.getType();
        List<UserSign> oldSignUId = gson.fromJson(signUId, type);

        if (oldSignUId==null){
            oldSignUId = new ArrayList<UserSign>();
        }
        //向签到列表增加
        oldSignUId.add(userSign);
        //更新数据库
        String newSignUId = gson.toJson(oldSignUId);
        userSignMapper.updateSignUId(userSign.getSignId(),newSignUId);
        sqlSession.close();
        return true;
    }

    public List<UserSigns> obtainCurrentSign(){

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserSignMapper userSignMapper = sqlSession.getMapper(UserSignMapper.class);

        Date now = new Date();
        List<UserSigns> userSigns = userSignMapper.selectAllByTime(now);
        sqlSession.close();
        return userSigns;
    }

    public boolean judgeStatus(String signUId, Integer studentId){
        if (signUId!=null&&!signUId.equals("null")&&!signUId.isEmpty()){
            Type type = new TypeToken<List<UserSign>>(){}.getType();
            List<UserSign> userSignList = gson.fromJson(signUId, type);
            System.out.println("judgeStatus:userSignList:"+userSignList);
            System.out.println("\n\njudgeStatus::studentId"+studentId);

            for (UserSign usersign :
                    userSignList) {
                if(usersign.getStudentId() == studentId){//签过
                    System.out.println("judgeStatus:usersign.getStudentId(): "+usersign.getSignId());
                    return true;
                }
            }
        }
        return false;//没签过
    }


    public List<UserSigns> getAppSignList(List<UserSigns> currentSignsList,Integer studentId){
        List<UserSigns> appSignList = new ArrayList<>();
        //有签到设置
        if(currentSignsList!=null&&!currentSignsList.isEmpty()){
            //查询用户是否签到过
            for (UserSigns userSigns : currentSignsList) {
                String signUId = userSigns.getSignUId();
                if(signUId==null||signUId.isEmpty()){
                    appSignList.add(userSigns);
                }else {
                    if (!judgeStatus(signUId,studentId)){
                        appSignList.add(userSigns);
                    }
                }
            }
        }
        return appSignList;
    }
    public boolean isOvertime(UserSign userSign){
        //配置mybatis
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserSignMapper userSignMapper = sqlSession.getMapper(UserSignMapper.class);

        //查询该签到的信息
        UserSigns userSigns = userSignMapper.selectById(userSign.getSignId());
        if (userSigns != null){
            if (userSigns.getEndTime().after(userSign.getSignTime())){//未超时
                //update数据库
                //去除signUId，增加signEntity
                String signUId = userSigns.getSignUId();
                Type type = new TypeToken<List<UserSign>>(){}.getType();
                List<UserSign> userSignList = gson.fromJson(signUId, type);
                if (userSignList == null){
                    userSignList = new ArrayList<>();
                }
                userSignList.add(userSign);

//                //更新数据库
//                String json = gson.toJson(userSignList);
//                userSignMapper.updateSignUId(userSign.getSignId(), json);
                sqlSession.close();
                return false;
            }
        }
        sqlSession.close();
        return true;
    }

}
