package com.fxxkyiwangchangxue.yiwangnochangxue.service;

import com.fxxkyiwangchangxue.yiwangnochangxue.CustomRequest;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.Teacher;
import com.fxxkyiwangchangxue.yiwangnochangxue.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter("/*")
public class WebAndroidFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(true);
        User user = (User) session.getAttribute("user");
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        HttpServletRequest newRequest = req;
        if (req.getHeader("AndroidApp") == null) {
            Map<String, String[]> paramMap = new HashMap<>(req.getParameterMap());
            if (user != null)
                paramMap.put("uid", new String[]{String.valueOf(user.getUid())});
            else if (teacher != null)
                paramMap.put("teacherId", new String[]{String.valueOf(teacher.getTeacherId())});
            newRequest = new CustomRequest(req, paramMap);
        }

        filterChain.doFilter(newRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}