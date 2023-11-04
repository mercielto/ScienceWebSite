package com.example.webprojectscience.servlet;

import com.example.webprojectscience.config.FreemarkerConfigSingleton;
import com.example.webprojectscience.config.NavbarMapGetter;
import com.example.webprojectscience.models.Question;
import com.example.webprojectscience.models.QuestionAnswer;
import com.example.webprojectscience.models.User;
import com.example.webprojectscience.models.joined.JoinedAnswer;
import com.example.webprojectscience.models.joined.JoinedQuestion;
import com.example.webprojectscience.service.AuthorizationService;
import com.example.webprojectscience.service.ForumAnswerHandlerService;
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
import java.util.Optional;

@WebServlet(name = "ForumAnswersServlet", value = "/forum/*")
public class ForumAnswersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        String link = req.getPathInfo().substring(1);
        JoinedQuestion joinedQuestion = ForumAnswerHandlerService.getJoinedQuestionByLink(link);
        List<JoinedAnswer> answers = ForumAnswerHandlerService.getJoinedAnswersByQuestionId(joinedQuestion.getId());
        Optional<User> optional = Optional.of(AuthorizationService.getAuthorizedUser(req));

        Map<String, Object> params = NavbarMapGetter.getMap(req);
        params.put("answers", answers);
        params.put("option", optional);
        params.put("question", joinedQuestion);

        Configuration cfg = FreemarkerConfigSingleton.getConfig();
        Template temp = cfg.getTemplate("forumAnswers.ftl");

        try {
            temp.process(params, resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }


}
