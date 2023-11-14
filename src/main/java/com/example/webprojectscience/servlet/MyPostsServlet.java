package com.example.webprojectscience.servlet;

import com.example.webprojectscience.config.FreemarkerConfigSingleton;
import com.example.webprojectscience.config.NavbarMapGetter;
import com.example.webprojectscience.models.Theme;
import com.example.webprojectscience.models.User;
import com.example.webprojectscience.models.joined.JoinedPost;
import com.example.webprojectscience.service.AuthorizationService;
import com.example.webprojectscience.service.PostsHandlerService;
import com.example.webprojectscience.service.ProfileHandlerService;
import com.example.webprojectscience.utill.Helpers;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "MyPostsServlet", value = "/my-posts")
public class MyPostsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        Configuration cfg = FreemarkerConfigSingleton.getConfig();
        Template temp = cfg.getTemplate("my-posts.ftl");

        User user = AuthorizationService.getAuthorizedUser(req);
        List<JoinedPost> posts = PostsHandlerService.getJoinedPostsByUserId(user.getId());
        List<Theme> themes = PostsHandlerService.getThemes();

        Map<String, Object> params = NavbarMapGetter.getMap(req, user);
        params.put("posts", posts);
        params.put("themes", themes);

        try {
            temp.process(params, resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        PostsHandlerService.createNewPost(req);

        Helpers.redirect(resp, req.getContextPath() + "\\my-posts");
    }
}
