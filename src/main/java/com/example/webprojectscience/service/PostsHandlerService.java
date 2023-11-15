package com.example.webprojectscience.service;

import com.example.webprojectscience.models.Post;
import com.example.webprojectscience.models.Subscription;
import com.example.webprojectscience.models.Theme;
import com.example.webprojectscience.models.User;
import com.example.webprojectscience.models.joined.JoinedPost;
import com.example.webprojectscience.utill.DataBaseManager;
import com.example.webprojectscience.utill.FileStoragePathBuilder;
import com.example.webprojectscience.utill.Generator;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PostsHandlerService {
    public static List<JoinedPost> getJoinedPosts() {
        return DataBaseManager.getPostDao().getJoinedAll();
    }

    public static JoinedPost getJoinedPostByLink(String link) {
        return DataBaseManager.getPostDao().getJoinedByLink(link);
    }

    public static List<JoinedPost> getJoinedPostsByUserId(Long userId) {
        return DataBaseManager.getPostDao().getJoinedByUserId(userId);
    }

    public static List<Theme> getThemes() {
        return DataBaseManager.getThemeDao().getAll();
    }

    public static void createNewPost(HttpServletRequest req) {
        Theme theme = DataBaseManager.getThemeDao().getByName(req.getParameter("theme"));
        User user = AuthorizationService.getAuthorizedUser(req);

        String tagsField = req.getParameter("tags");
        String[] tags;
        if (tagsField == null) {
            tags = new String[0];
        } else {
            tags = tagsField.split(" ");
        }

        if (theme == null) {
            throw new RuntimeException("Theme not found");
        }

        Post post = new Post();
        post.setUserId(user.getId());
        post.setTitle(req.getParameter("title"));
        post.setThemeId(theme.getId());
        post.setTags(List.of(tags));

        String fileName = Generator.generateFilePostName(user);

        String filePath = FileStoragePathBuilder.getPathToPostTxt(fileName);
        createFile(filePath, req.getParameter("text"));
        post.setPathInStorage(fileName);
        post.setDate(Date.valueOf(LocalDate.now()));

        String link = Generator.generateLink(user);
        post.setLink(link);

        DataBaseManager.getPostDao().insert(post);
    }

    public static void createFile(String path, String value) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
            bufferedWriter.write(value);
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<JoinedPost> getJoinedPostsBySubscriptions(User user) {
        List<Subscription> subscriptions = DataBaseManager.getSubscriptionDao().getSubscriptions(user.getId());

        List<Long> userIdList = new ArrayList<>();
        for (Subscription subscription : subscriptions) {
            userIdList.add(subscription.getUserId());
        }

        return DataBaseManager.getPostDao().getJoinedByAuthorId(userIdList);
    }
}
