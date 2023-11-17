package com.example.webprojectscience.servlet.ajax;

import com.example.webprojectscience.config.FreemarkerConfigSingleton;
import com.example.webprojectscience.config.NavbarMapGetter;
import com.example.webprojectscience.models.Comment;
import com.example.webprojectscience.models.User;
import com.example.webprojectscience.models.joined.JoinedComment;
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
import java.util.Map;

@WebServlet(name = "CommentsAjaxService", value = "/ajax/commentPush")
public class CommentsAjaxService extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        AjaxHandlerService.saveComment(req);
    }
}
