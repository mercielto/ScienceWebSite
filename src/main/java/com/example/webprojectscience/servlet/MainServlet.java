package com.example.webprojectscience.servlet;

import com.example.webprojectscience.config.FreemarkerConfigSingleton;
import com.example.webprojectscience.config.NavbarMapGetter;
import com.example.webprojectscience.models.User;
import com.example.webprojectscience.models.regular.Block;
import com.example.webprojectscience.service.AuthorizationService;
import com.example.webprojectscience.service.MainPageService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "MainServlet", value = "/")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        List<Block> blockList = new ArrayList<>();
        blockList.add(new Block("Users count", MainPageService.getUsersCount()));
        blockList.add(new Block("Users online", MainPageService.getOnlineUserCount()));
        blockList.add(new Block("Posts", MainPageService.getPostsCount()));
        blockList.add(new Block("Questions answered", MainPageService.getAnsweredQuestionsCount()));

        User user = AuthorizationService.getAuthorizedUser(req);
        Map<String, Object> params = NavbarMapGetter.getMap(req, user);
        params.put("blocks", blockList);

        Configuration cfg = FreemarkerConfigSingleton.getConfig();
        Template temp = cfg.getTemplate("main-page.ftl");

        try {
            temp.process(params, resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }

    }
}
