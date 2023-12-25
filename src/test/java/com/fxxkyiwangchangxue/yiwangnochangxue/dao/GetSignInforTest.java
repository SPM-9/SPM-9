package com.fxxkyiwangchangxue.yiwangnochangxue.dao;

import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.UserSignMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.UserSign;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.UserSigns;
import com.fxxkyiwangchangxue.yiwangnochangxue.service.sign.GetSignInfor;
import com.google.gson.Gson;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.*;

import java.util.Date;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GetSignInforTest {
    Gson gson;
    GetSignInfor getSignInfor;


    @BeforeAll
    public void initialization(){
        getSignInfor = new GetSignInfor();
        gson =new Gson();
//        Date date = new Date();
//        Date date1 = new Date();
//        userSign = new UserSign(2, 1, date1, null);


//        System.out.println("date:"+date);
//        System.out.println("date1:"+date1);

//        String json = gson.toJson(userSigns);
//        userSigns = new UserSigns(1,1,json,date,date1);
    }
    @Test
    void testGson(){
        UserSign userSign = new UserSign(1,1,new Date(),null);
        String json = gson.toJson(userSign);
        UserSign userSign1 = gson.fromJson(json, UserSign.class);

        System.out.println(userSign1);
    }
    @Test
    void testObtainCurrentSign(){
//        List<UserSigns> userSigns1 = getSignInfor.obtainCurrentSign();
//        System.out.println(userSigns1);
        String testGson = null;
        UserSign userSign = gson.fromJson(testGson, UserSign.class);
        System.out.println(userSign);
    }
    @Test
    void testJudgeStatus(){
        String signUId = "[{\"signId\":2,\"studentId\":2},{\"signId\":2,\"studentId\":2},{\"signId\":2,\"studentId\":1,\"signTime\":\"Dec 8, 2023, 4:32:26 PM\"}]";
        Integer studentId = 3;

        boolean b = getSignInfor.judgeStatus(signUId, studentId);
        System.out.println(b);

    }

    @Test
    void testGetAppSignList(){
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserSignMapper userSignMapper = sqlSession.getMapper(UserSignMapper.class);
        List<UserSigns> userSigns = userSignMapper.selectAll();

        Integer studentId = 2;

        List<UserSigns> appSignList = getSignInfor.getAppSignList(userSigns, studentId);
        System.out.println("appSignList:"+appSignList);
    }

    @Test
    void testIsOvertime(){
//        Date date = new Date();
//        UserSign userSign = new UserSign(2,1,date,null);
//        boolean overtime = getSignInfor.isOvertime(userSign);
//        System.out.println(overtime);

        List<UserSigns> currentSignsList = getSignInfor.obtainCurrentSign();
        System.out.println("test:currentSignsList"+currentSignsList);

        List<UserSigns> appSignList = getSignInfor.getAppSignList(currentSignsList, 2);
        System.out.println("test:appSignList"+appSignList);


        //响应当前需要签到的签到列表
        String appSignListJson = new Gson().toJson(appSignList);
        System.out.println("test:appSignListJson:"+appSignListJson);
    }

//    void test

}
