package com.example.webprojectscience.servlet;

import com.example.webprojectscience.config.FreemarkerConfigSingleton;
import com.example.webprojectscience.config.NavbarMapGetter;
import com.example.webprojectscience.models.Like;
import com.example.webprojectscience.models.Post;
import com.example.webprojectscience.models.Subscription;
import com.example.webprojectscience.models.User;
import com.example.webprojectscience.service.AuthorizationService;
import com.example.webprojectscience.service.ProfileHandlerService;
import com.example.webprojectscience.utill.DataBaseManager;
import com.example.webprojectscience.utill.ImageBuilder;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@WebServlet(name = "ProfileServlet", value = "/profile/*")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String link = req.getPathInfo().substring(1);
        User profileUser = DataBaseManager.getUserDao().getByLink(link);

        if (profileUser == null) {
            resp.sendError(404);
            return;
        }

        Optional<User> option = Optional.ofNullable(AuthorizationService.getAuthorizedUser(req));

        Configuration cfg = FreemarkerConfigSingleton.getConfig();
        Template temp = cfg.getTemplate("profile.ftl");

        boolean subscribed;
        if (option.isEmpty()) {
            subscribed = false;
        } else {
            subscribed = ProfileHandlerService.isSubscribed(option.get(), profileUser);
        }

        List<User> profileUserSubscriptions = ProfileHandlerService.getUserSubscriptions(profileUser);
        List<User> profileUserSubscribers = ProfileHandlerService.getUserSubscribers(profileUser);
        List<Like> profileUserLikes = ProfileHandlerService.getLikes(profileUser);
        List<Post> profileUserPosts = ProfileHandlerService.getPosts(profileUser);

        Map<String, Object> params = NavbarMapGetter.getMap(req);
        params.put("option", option);
        params.put("subscribed", subscribed);
        params.put("profileUser", profileUser);
        params.put("profileUserSubscriptions", profileUserSubscriptions);
        params.put("profileUserSubscriber", profileUserSubscribers);
        params.put("profileUserLikes", profileUserLikes);
        params.put("profileUserPosts", profileUserPosts);

        try {
            temp.process(params, resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }

        //option, storage.., proc, profileUser, subscribed  - boolean, profileUserSubscriptions (List<User>), profileUserLikes, profileUserPosts



    }

    // сделать через ajax
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String subscribe = req.getParameter("subscriber");
        String link = req.getPathInfo().substring(1);

        User user = AuthorizationService.getAuthorizedUser(req);
        User profileUser = DataBaseManager.getUserDao().getByLink(link);

        Subscription subscription = DataBaseManager.getSubscriptionDao().getSubsctiption(user.getId(), profileUser.getId());
        if (subscription != null) {
            DataBaseManager.getSubscriptionDao().delete(subscription.getId());
        } else {
            subscription = new Subscription();
            subscription.setUserId(profileUser.getId());
            subscription.setSubscriberId(user.getId());
            DataBaseManager.getSubscriptionDao().insert(subscription);
        }
    }
}
