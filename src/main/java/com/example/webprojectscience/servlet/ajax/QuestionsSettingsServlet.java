package com.example.webprojectscience.servlet.ajax;

import com.example.webprojectscience.config.FreemarkerConfigSingleton;
import com.example.webprojectscience.config.NavbarMapGetter;
import com.example.webprojectscience.models.User;
import com.example.webprojectscience.models.joined.JoinedPost;
import com.example.webprojectscience.models.joined.JoinedQuestion;
import com.example.webprojectscience.service.AjaxHandlerService;
import com.example.webprojectscience.service.AuthorizationService;
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

@WebServlet(name = "QuestionsSettingsServlet", value = "/ajax/settings/questions")
public class QuestionsSettingsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        List<JoinedQuestion> posts = AjaxHandlerService.getQuestions(req);

        Configuration cfg = FreemarkerConfigSingleton.getConfig();
        Template temp = cfg.getTemplate("settingsAjaxQuestion.ftl");

        User user = AuthorizationService.getAuthorizedUser(req);
        Map<String, Object> params = NavbarMapGetter.getMap(req, user);
        params.put("questions", posts);

        try {
            temp.process(params, resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
