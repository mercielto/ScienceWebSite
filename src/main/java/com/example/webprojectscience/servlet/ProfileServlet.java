package com.example.webprojectscience.servlet;

import com.example.webprojectscience.config.FreemarkerConfigSingleton;
import com.example.webprojectscience.config.NavbarMapGetter;
import com.example.webprojectscience.models.Subscription;
import com.example.webprojectscience.models.User;
import com.example.webprojectscience.service.AuthorizationService;
import com.example.webprojectscience.service.ProfileHandlerService;
import com.example.webprojectscience.utill.DataBaseManager;
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

@WebServlet(name = "ProfileServlet", value = "/profile/*")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        String link = req.getPathInfo().substring(1);
        User profileUser = ProfileHandlerService.getUserByLink(link);

        if (profileUser == null) {
            resp.sendError(404);
            return;
        }

        Configuration cfg = FreemarkerConfigSingleton.getConfig();
        Template temp = cfg.getTemplate("profile.ftl");

        User user = AuthorizationService.getAuthorizedUser(req);
        Map<String, Object> params = NavbarMapGetter.getMap(req, user);

        boolean subscribed;
        if (user == null) {
            subscribed = false;
        } else {
            subscribed = ProfileHandlerService.isSubscribed(user, profileUser);
        }

        ProfileHandlerService.addProfileParamsToMap(params, profileUser);
        params.put("subscribed", subscribed);

        try {
            temp.process(params, resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }

        //option, storage.., proc, profileUser, subscribed  - boolean, profileUserSubscriptions (List<User>), profileUserLikes, profileUserPosts
    }
}
