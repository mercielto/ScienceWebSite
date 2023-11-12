package com.example.webprojectscience.servlet;

import com.example.webprojectscience.config.FreemarkerConfigSingleton;
import com.example.webprojectscience.config.NavbarMapGetter;
import com.example.webprojectscience.models.User;
import com.example.webprojectscience.models.joined.JoinedPost;
import com.example.webprojectscience.service.AuthorizationService;
import com.example.webprojectscience.service.PostsHandlerService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "PostSingleServlet", value = "/posts/*")
public class PostSingleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        String link = req.getPathInfo().substring(1);
        if (link.equals("")) {
            resp.sendError(404);
            return;
        }

        JoinedPost joinedPost = PostsHandlerService.getJoinedPostByLink(link);

        Configuration cfg = FreemarkerConfigSingleton.getConfig();
        Template temp = cfg.getTemplate("post-single.ftl");

        User user = AuthorizationService.getAuthorizedUser(req);
        Map<String, Object> params = NavbarMapGetter.getMap(req, user);
        params.put("post", joinedPost);

        try {
            temp.process(params, resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
