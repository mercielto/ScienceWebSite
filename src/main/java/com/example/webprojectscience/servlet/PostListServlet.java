package com.example.webprojectscience.servlet;

import com.example.webprojectscience.config.FreemarkerConfigSingleton;
import com.example.webprojectscience.config.NavbarMapGetter;
import com.example.webprojectscience.models.Theme;
import com.example.webprojectscience.models.User;
import com.example.webprojectscience.models.joined.JoinedPost;
import com.example.webprojectscience.service.AuthorizationService;
import com.example.webprojectscience.service.PostsHandlerService;
import com.example.webprojectscience.utill.DataBaseManager;
import com.example.webprojectscience.utill.FileBuilder;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@WebServlet(name = "PostListServlet", value = "/posts")
public class PostListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        Optional<User> user =  Optional.ofNullable(AuthorizationService.getAuthorizedUser(req));
        List<JoinedPost> posts = PostsHandlerService.getJoinedPosts();
        List<Theme> themes = DataBaseManager.getThemeDao().getAll();

        Map<String, Object> params = NavbarMapGetter.getMap(req);
        params.put("option", user);
        params.put("posts", posts);
        params.put("themes", themes);


        Configuration cfg = FreemarkerConfigSingleton.getConfig();
        Template temp = cfg.getTemplate("posts.ftl");

        try {
            temp.process(params, resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
