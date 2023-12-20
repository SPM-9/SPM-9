package com.fxxkyiwangchangxue.yiwangnochangxue.service;

import java.io.*;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.io.IOUtils;

@WebServlet(name = "DownloadAPK", value = "/DownloadAPK")
public class DownloadAPK extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/vnd.android");
        resp.setHeader("Content-Disposition","attachment;filename=NoStudy.apk");

        ServletContext context = getServletContext();
        BufferedInputStream bis = new BufferedInputStream(context.getResourceAsStream("/NoStudy.apk"));
        BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
        try (bis ; bos){
            IOUtils.copy(bis, bos);
        } catch (IOException e) {
            e.printStackTrace();
            resp.setStatus(500);
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