package com.example.webprojectscience.service;

import com.example.webprojectscience.models.Like;
import com.example.webprojectscience.models.Post;
import com.example.webprojectscience.models.Subscription;
import com.example.webprojectscience.models.User;
import com.example.webprojectscience.utill.DataBaseManager;

import java.util.ArrayList;
import java.util.List;

public class ProfileHandlerService {
    public static boolean isSubscribed(User user, User subscribedTo) {
        return DataBaseManager.getSubscriptionDao().isSubscribed(user.getId(), subscribedTo.getId());
    }

    public static List<User> getUserSubscriptions(User user) {
        List<User> users = new ArrayList<>();
        List<Subscription> subscriptions = DataBaseManager.getSubscriptionDao().getSubscriptions(user.getId());

        for (Subscription subscription : subscriptions) {
            User subscr = DataBaseManager.getUserDao().getById(subscription.getUserId());
            users.add(subscr);
        }

        return users;
    }

    public static List<User> getUserSubscribers(User user) {
        List<User> users = new ArrayList<>();
        List<Subscription> subscriptions = DataBaseManager.getSubscriptionDao().getSubscribers(user.getId());

        for (Subscription subscription : subscriptions) {
            User sub = DataBaseManager.getUserDao().getById(subscription.getSubscriberId());
            users.add(sub);
        }

        return users;
    }

    public static List<Like> getLikes(User user) {
        return DataBaseManager.getLikeDao().getLikesById(user.getId());
    }

    public static List<Post> getPosts(User user) {
        return DataBaseManager.getPostDao().getByUserId(user.getId());
    }
}
