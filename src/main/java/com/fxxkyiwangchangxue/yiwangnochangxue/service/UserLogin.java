package com.fxxkyiwangchangxue.yiwangnochangxue.service;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "UserLogin", value = "/UserLogin")
public class UserLogin extends HttpServlet {
    Connection conn;
    ResultSet rs;
    PreparedStatement ps;

    @Override
    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        String userName = req.getParameter("userName").trim();
        String password = req.getParameter("password").trim();

        String query = "SELECT * FROM `User` WHERE `userName` = ? && `password` = ?";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://1.15.179.230:3306/VideoPlayer?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "root", "114514");

            ps = conn.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2,password);
            rs = ps.executeQuery();

            if (rs.next()) {
                // 登录成功
                Gson json = new Gson();
//                _4_UserBean userEntity = new _4_UserBean(
//                        String.valueOf(rs.getInt("id")),
//                        rs.getString("userName"),
//                        rs.getString("userEmail"),
//                        rs.getString("password"),
//                        rs.getString("userNickName"),
//                        rs.getInt("membership"),
//                        rs.getString("favorite"));
//                pw.print(json.toJson(userEntity));
            } else {
                // 登录失败
                pw.print("false");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            resp.setStatus(500);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        resp.setStatus(404);
    }

    @Override
    public void destroy() {

    }
}