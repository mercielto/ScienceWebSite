package com.example.webprojectscience.servlet;

import com.example.webprojectscience.config.FreemarkerConfigSingleton;
import com.example.webprojectscience.config.NavbarMapGetter;
import com.example.webprojectscience.models.User;
import com.example.webprojectscience.service.AuthorizationService;
import com.example.webprojectscience.service.ProfileHandlerService;
import com.example.webprojectscience.utill.DataBaseManager;
import com.example.webprojectscience.utill.Helpers;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.Map;

@WebServlet(name = "ProfileSettingsServlet", value = "/profile/settings/*")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class ProfileSettingsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        String link = req.getPathInfo().substring(1);
        User profileUser = DataBaseManager.getUserDao().getByLink(link);
        User user = AuthorizationService.getAuthorizedUser(req);

        if (!user.equals(profileUser)) {
            Helpers.redirect(resp, req.getContextPath() + "/profile/" + profileUser.getLink());
            return;
        }

        Configuration cfg = FreemarkerConfigSingleton.getConfig();
        Template temp = cfg.getTemplate("profileSettings.ftl");

        Map<String, Object> params = NavbarMapGetter.getMap(req, user);
        ProfileHandlerService.addProfileParamsToMap(params, profileUser);

        try {
            temp.process(params, resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        User user = AuthorizationService.getAuthorizedUser(req);

        String name = req.getParameter("name");
        String description = req.getParameter("description");
        Part img = req.getPart("img");

        if (description.equals("")) {
            description = null;
        }

        ProfileHandlerService.updateProfilePhoto(img, user);
        user.setName(name);
        user.setDescription(description);

        ProfileHandlerService.updateUserParams(user);

        Helpers.redirect(resp, req.getContextPath() + "\\profile\\settings\\" + user.getLink());
    }
}
