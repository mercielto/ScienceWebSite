package com.example.webprojectscience.servlet;

import com.example.webprojectscience.models.User;
import com.example.webprojectscience.service.AuthorizationService;
import com.example.webprojectscience.utill.DataBaseManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProfileSettingsServlet", value = "/profile/settings/*")
public class ProfileSettingsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        String link = req.getPathInfo().substring(1);
        User profileUser = DataBaseManager.getUserDao().getByLink(link);
        User user = AuthorizationService.getAuthorizedUser(req);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
