package com.example.webprojectscience.servlet.ajax;

import com.example.webprojectscience.utill.FileBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProfileSettingsAjaxServlet", value = "/ajax/getDefaultProfileImage")
public class ProfileSettingsAjaxServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FileBuilder builder = new FileBuilder();
        String ans = builder.getProfilePhotoInBytes("default.jpg");
        resp.getWriter().write(ans);
    }
}
