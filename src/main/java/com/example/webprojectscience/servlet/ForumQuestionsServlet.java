package com.example.webprojectscience.servlet;

import com.example.webprojectscience.config.FreemarkerConfigSingleton;
import com.example.webprojectscience.config.NavbarMapGetter;
import com.example.webprojectscience.dao.DAO;
import com.example.webprojectscience.models.JoinedQuestion;
import com.example.webprojectscience.models.Question;
import com.example.webprojectscience.models.User;
import com.example.webprojectscience.service.AuthorizationService;
import com.example.webprojectscience.service.ForumQuestionsSelectorService;
import com.example.webprojectscience.utill.DataBaseManager;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "ForumQuestionsServlet", value = "/forum")
public class ForumQuestionsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");

        User user = AuthorizationService.getAuthorizedUser(req);

        Configuration cfg = FreemarkerConfigSingleton.getConfig();
        Template temp = cfg.getTemplate("forumMain.ftl");

        List<JoinedQuestion> questions = ForumQuestionsSelectorService.getJoinedQuestions(
                null, null, null, new ArrayList<>(), 10);

        Map<String, Object> params = NavbarMapGetter.getMap(req);
        params.put("questions", questions);
        params.put("option", Optional.ofNullable(user));
        params.put("pageNumbers", ForumQuestionsSelectorService.getPageNumbers(questions.size()));
        params.put("themes", ForumQuestionsSelectorService.getThemes());

        try {
            temp.process(params, resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
