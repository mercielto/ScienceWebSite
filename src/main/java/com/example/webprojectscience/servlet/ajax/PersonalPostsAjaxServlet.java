package com.example.webprojectscience.servlet.ajax;

import com.example.webprojectscience.config.FreemarkerConfigSingleton;
import com.example.webprojectscience.config.NavbarMapGetter;
import com.example.webprojectscience.models.User;
import com.example.webprojectscience.models.joined.JoinedPost;
import com.example.webprojectscience.service.AjaxHandlerService;
import com.example.webprojectscience.service.AuthorizationService;
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

@WebServlet(name = "PersonalPostsAjaxServlet", value = "/ajax/personal-posts")
public class PersonalPostsAjaxServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        User user = AuthorizationService.getAuthorizedUser(req);
        Map<String, Object> params = NavbarMapGetter.getMap(req, user);

        List<JoinedPost> posts = AjaxHandlerService.getPersonalJoinedPosts(req);

        Configuration cfg = FreemarkerConfigSingleton.getConfig();
        Template temp = cfg.getTemplate("settingsAjaxPost.ftl");

        params.put("posts", posts);

        try {
            temp.process(params, resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
