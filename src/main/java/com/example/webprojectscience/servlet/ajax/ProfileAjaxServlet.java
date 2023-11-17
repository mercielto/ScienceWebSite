package com.example.webprojectscience.servlet.ajax;

import com.example.webprojectscience.models.Subscription;
import com.example.webprojectscience.models.User;
import com.example.webprojectscience.service.AuthorizationService;
import com.example.webprojectscience.utill.DataBaseManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProfileAjaxServlet", value = "/ajax/profile")
public class ProfileAjaxServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] p = req.getParameter("link").split("/");
        String link = p[p.length - 1];

        User user = AuthorizationService.getAuthorizedUser(req);
        User profileUser = DataBaseManager.getUserDao().getByLink(link);
        Subscription subscription = DataBaseManager.getSubscriptionDao().getSubscription(user.getId(), profileUser.getId());

        if (subscription != null) {
            DataBaseManager.getSubscriptionDao().delete(subscription.getId());
            resp.getWriter().write(
                    "<button name=\"not_subscribed\" onclick=\"changeStateBtn()\" id=\"subscribe-button\" class=\"profile-notSubscribed rounded profile-subscribe-size\">Subscribe</button>"
            );
        } else {
            subscription = new Subscription();
            subscription.setUserId(profileUser.getId());
            subscription.setSubscriberId(user.getId());
            DataBaseManager.getSubscriptionDao().insert(subscription);
            resp.getWriter().write(
                    "<button name=\"subscribed\" onclick=\"changeStateBtn()\" id=\"subscribe-button\" class=\"profile-subscribed rounded profile-subscribe-size\" ${disabled}>Subscribed</button>"
            );
        }
    }
}
