package com.example.webprojectscience.servlet;

import com.example.webprojectscience.config.FreemarkerConfigSingleton;
import com.example.webprojectscience.config.NavbarMapGetter;
import com.example.webprojectscience.models.Theme;
import com.example.webprojectscience.models.User;
import com.example.webprojectscience.models.joined.JoinedQuestion;
import com.example.webprojectscience.service.AuthorizationService;
import com.example.webprojectscience.service.ForumQuestionsSelectorService;
import com.example.webprojectscience.service.PostsHandlerService;
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
import java.util.List;
import java.util.Map;

@WebServlet(name="MyQuestionsServlet", value="/my-questions")
public class MyQuestionsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        User user = AuthorizationService.getAuthorizedUser(req);
        List<JoinedQuestion> questions = ForumQuestionsSelectorService.getJoinedQuestionsByUserId(user.getId());
        List<Theme> themes = ForumQuestionsSelectorService.getThemes();

        Map<String, Object> params = NavbarMapGetter.getMap(req, user);
        params.put("questions", questions);
        params.put("themes", themes);

        Configuration cfg = FreemarkerConfigSingleton.getConfig();
        Template temp = cfg.getTemplate("my-questions.ftl");

        try {
            temp.process(params, resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        ForumQuestionsSelectorService.createNewQuestion(req);

        Helpers.redirect(resp, req.getContextPath() + "\\my-questions");
    }
}
