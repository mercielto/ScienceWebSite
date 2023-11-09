package com.example.webprojectscience.service;

import com.example.webprojectscience.config.Params;
import com.example.webprojectscience.models.Like;
import com.example.webprojectscience.models.Post;
import com.example.webprojectscience.models.Subscription;
import com.example.webprojectscience.models.User;
import com.example.webprojectscience.utill.DataBaseManager;
import com.example.webprojectscience.utill.FileStoragePathBuilder;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    public static List<Post> getLikedPosts(User user) {
        List<Like> likes = DataBaseManager.getLikeDao().getLikesByUserId(user.getId());
        List<Post> posts = new ArrayList<>();

        for (Like like : likes) {
            posts.add(
                    DataBaseManager.getPostDao().getById(like.getPostId())
            );
        }
        return posts;
    }

    public static List<Post> getPosts(User user) {
        return DataBaseManager.getPostDao().getByUserId(user.getId());
    }

    public static User getUserByLink(String link) {
        return DataBaseManager.getUserDao().getByLink(link);
    }

    public static void addProfileParamsToMap(Map<String, Object> params, User user) {
        List<User> profileUserSubscriptions = ProfileHandlerService.getUserSubscriptions(user);
        List<User> profileUserSubscribers = ProfileHandlerService.getUserSubscribers(user);
        List<Post> profileUserLikedPosts = ProfileHandlerService.getLikedPosts(user);
        List<Post> profileUserPosts = ProfileHandlerService.getPosts(user);

        params.put("profileUser", user);
        params.put("profileUserSubscriptions", profileUserSubscriptions);
        params.put("profileUserSubscriber", profileUserSubscribers);
        params.put("profileUserLikedPosts", profileUserLikedPosts);
        params.put("profileUserPosts", profileUserPosts);
    }

    public static void updateProfilePhoto(Part img, User user) {
        String[] fileName = img.getSubmittedFileName().split("\\.");
        String fileType = fileName[fileName.length - 1];
        String newFileName = user.getLogin() + "." + fileType;

        try {
            BufferedImage image = ImageIO.read(img.getInputStream());
            if (image == null) {
                return;
            }

            ImageIO.write(image , fileType, new File(
                    FileStoragePathBuilder.getPathToProfileImage(newFileName)
            ));

            if (!user.getProfilePhotoPath().equals(newFileName) && !user.getProfilePhotoPath().equals(Params.defaultProfilePhotoName)) {
                File oldFile = new File(
                        FileStoragePathBuilder.getPathToProfileImage(user.getProfilePhotoPath())
                );
                oldFile.delete();
            }

            user.setProfilePhotoPath(newFileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateUserParams(User user) {
        DataBaseManager.getUserDao().update(user);
    }
}
