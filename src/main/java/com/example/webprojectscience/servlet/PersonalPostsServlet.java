package com.example.webprojectscience.servlet;

import com.example.webprojectscience.config.FreemarkerConfigSingleton;
import com.example.webprojectscience.config.NavbarMapGetter;
import com.example.webprojectscience.models.User;
import com.example.webprojectscience.service.AuthorizationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.example.webprojectscience.models.joined.JoinedPost;
import com.example.webprojectscience.service.PostsHandlerService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@WebServlet(name = "PersonalPostsServlet", value = "/personal-posts")
public class PersonalPostsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        User user = AuthorizationService.getAuthorizedUser(req);
        List<JoinedPost> posts = PostsHandlerService.getJoinedPostsBySubscriptions(user);

        Map<String, Object> params = NavbarMapGetter.getMap(req, user);
        params.put("posts", posts);

        Configuration cfg = FreemarkerConfigSingleton.getConfig();
        Template temp = cfg.getTemplate("personal-posts.ftl");

        try {
            temp.process(params, resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
