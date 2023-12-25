package com.fxxkyiwangchangxue.yiwangnochangxue.dao;

import com.fxxkyiwangchangxue.yiwangnochangxue.dao.mapper.UserSignMapper;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.UserSign;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.UserSigns;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.*;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserSignMapperTest {
    static UserSignMapper userSignMapper;
    static SqlSession sqlSession;
    static Gson gson;

    @BeforeAll
    public void getMapper(){
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        sqlSession = sqlSessionFactory.openSession(true);
        userSignMapper = sqlSession.getMapper(UserSignMapper.class);
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

    @Test
    public void testUpdateSignUId(){
        Integer id = 2;
        Type type = new TypeToken<List<UserSign>>(){}.getType();
        List<UserSign> userSignList = new ArrayList<UserSign>();
        userSignList.add(new UserSign(2, 2, null, null));
        userSignList.add(new UserSign(2, 2, null, null));
        String json = gson.toJson(userSignList);
        userSignMapper.updateSignUId(id,json);
        //UI查看是否更新
    }

    @Test
    void testSelectById(){
        Integer id = 1;
        UserSigns userSigns = userSignMapper.selectById(id);
        System.out.println("byId结果："+userSigns);
        Assertions.assertEquals( id, userSigns.getSignId(),"byId方法出错：");
    }

    @Test
    void testSelectAllByTime(){
//        Date date = new Date();
//
//        List<UserSigns> userSigns = userSignMapper.selectAllByTime(date);
//
//        System.out.println("byTime结果:"+userSigns);


    }

    @AfterAll
    void closeSession(){
        sqlSession.close();
    }

    @Test
    void testInsertNewSign() throws SQLException, UnsupportedEncodingException {
        Calendar calendar=Calendar.getInstance();
        calendar.set(2024, 1, 12);  //年月日  也可以具体到时分秒如calendar.set(2015, 10, 12,11,32,52);
        Date date=calendar.getTime();//date就是你需要的时间
        Calendar calendar1=Calendar.getInstance();
        calendar1.set(2024, 1, 10);  //年月日  也可以具体到时分秒如calendar.set(2015, 10, 12,11,32,52);
        Date date1=calendar.getTime();//date就是你需要的时间
        UserSigns userSigns = new UserSigns(null, 1, null, date1, date);
        String json = gson.toJson(userSigns);
        String json1 = URLEncoder.encode(json, "UTF-8");
        System.out.println(json);
//        boolean b;
//        try {
//            b = userSignMapper.insertNewSign(userSigns);
//        }catch (Exception e){
//            b = false;
//            System.out.println(e);
//        }
//        System.out.println("b:"+b);
//        System.out.println("返回的主键："+userSigns.getSignId());
    }

}
