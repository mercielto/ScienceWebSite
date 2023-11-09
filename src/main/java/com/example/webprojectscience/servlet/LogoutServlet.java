package com.example.webprojectscience.servlet;

import com.example.webprojectscience.models.User;
import com.example.webprojectscience.service.AuthorizationService;
import com.example.webprojectscience.utill.Helpers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LogoutServlet", value = "/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = AuthorizationService.getAuthorizedUser(req);
        if (user == null) {
            Helpers.redirect(resp, req.getContextPath());
            return;
        }
        AuthorizationService.logout(req, resp, user);
        Helpers.redirect(resp, req.getContextPath());
    }
}
