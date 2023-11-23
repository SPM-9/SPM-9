package com.fxxkyiwangchangxue.yiwangnochangxue.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "UserRegister", value = "/UserRegister")
public class UserRegister extends HttpServlet {
    Connection conn;
    PreparedStatement ps;

    @Override
    public void init() {

    }

    String query = "INSERT INTO User (`userName`, `userEmail`, `password`, `userNickName`) VALUES (?, ?, ?, ?)";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset = UTF-8");
        PrintWriter pw = resp.getWriter();

        String userName = req.getParameter("userName");
        String userEmail = req.getParameter("userEmail");
        String password = req.getParameter("password");
        String userNickName = req.getParameter("userNickName");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://1.15.179.230:3306/VideoPlayer?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "root", "114514");

            ps = conn.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2, userEmail);
            ps.setString(3, password);
            ps.setString(4, userNickName);
            ps.execute();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            resp.setStatus(500);
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
                resp.setStatus(500);
            }
        }
        pw.print(true);
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