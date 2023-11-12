package com.example.webprojectscience.servlet;

import com.example.webprojectscience.config.FreemarkerConfigSingleton;
import com.example.webprojectscience.config.NavbarMapGetter;
import com.example.webprojectscience.models.Theme;
import com.example.webprojectscience.models.User;
import com.example.webprojectscience.service.AuthorizationService;
import com.example.webprojectscience.service.ThemesHandlerService;
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

@WebServlet(name = "MainServlet", value = "/themes")
public class ThemesServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        Optional<User> user = Optional.ofNullable(AuthorizationService.getAuthorizedUser(req)); /*Optional.ofNullable(DataBaseManager.getUserDao().getByLogin("1"));*/
        List<Theme> themes = ThemesHandlerService.getAllThemes();
        Theme other = ThemesHandlerService.getOther();

        Configuration cfg = FreemarkerConfigSingleton.getConfig();
        Template temp = cfg.getTemplate("themes.ftl");

        Map<String, Object> params = NavbarMapGetter.getMap(req, user.get());
        params.put("option", user);
        params.put("themes", themes);
        params.put("other", other);
        params.put("helpers", new FileBuilder());

        try {
            temp.process(params, resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
