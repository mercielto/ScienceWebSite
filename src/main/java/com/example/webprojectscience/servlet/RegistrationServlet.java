package com.example.webprojectscience.servlet;

import com.example.webprojectscience.config.FreemarkerConfigSingleton;
import com.example.webprojectscience.config.NavbarMapGetter;
import com.example.webprojectscience.service.AuthorizationService;
import com.example.webprojectscience.service.RegistrationService;
import com.example.webprojectscience.utill.Helpers;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "RegistrationServlet", value = "/register")
public class RegistrationServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        Configuration cfg = FreemarkerConfigSingleton.getConfig();
        Template temp = cfg.getTemplate("registration.ftl");
        Map<String, Object> params = NavbarMapGetter.getMap(req, null);

        try {
            temp.process(params, resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password1 = req.getParameter("password1");
        String password2 = req.getParameter("password2");

        if (!password1.equals(password2)) {
            Helpers.redirect(resp, req.getContextPath() + "/register");
            return;
        }

        if (RegistrationService.registerUser(login, password1)) {
            Helpers.redirect(resp, req.getContextPath() + "/login");
        } else {
            Helpers.redirect(resp, req.getContextPath() + "/register");
        }

    }
}
