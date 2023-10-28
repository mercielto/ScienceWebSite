package com.example.webprojectscience.servlet;

import com.example.webprojectscience.config.FreemarkerConfigSingleton;
import com.example.webprojectscience.service.AuthorizationService;
import com.example.webprojectscience.utill.Helpers;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");

        if (AuthorizationService.authorizeByCookieToken(req)) {
            Helpers.redirect(resp, req.getContextPath() + "/menu");
            return;
        }

        Configuration cfg = FreemarkerConfigSingleton.getConfig();
        Template temp = cfg.getTemplate("login.ftl");

        try {
            temp.process(new HashMap<>(), resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String rememberMe = req.getParameter("remember_me");

        if (AuthorizationService.login(req)) {
            if (rememberMe != null) {
                AuthorizationService.saveInCookies(req, resp);
            } else {
                AuthorizationService.deleteCookies(req, resp, List.of("authorized"));
            }

            Helpers.redirect(resp, req.getContextPath() + "/themes");
        } else {
            Helpers.redirect(resp, req.getContextPath() + "/login");
        }
    }
}
