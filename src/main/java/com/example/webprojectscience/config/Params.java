package com.example.webprojectscience.config;

public class Params {
    public static String url = "jdbc:postgresql://localhost:5432/ScienceWebSite";
    public static String user = "postgres";
    public static String password = "22345";

    public static String userTableName = "User";
    public static String commentTableName = "comment";
    public static String friendTableName = "friend";
    public static String messageTableName = "message";
    public static String postTableName = "post";
    public static String subscriptionTableName = "subscription";
    public static String themeTableName = "theme";
    public static String tokenTableName = "token";
    public static String likeTableName = "Like";
    public static String forumQuestionTableName = "forum_question";
    public static String forumQuestionAnswerTableName = "forum_answer";

    public static String storagePath = "C:\\Works\\WebServer\\storage\\";
    public static String defaultProfilePhotoName = "default.jpg";

    public static String authorizationField = "authorized";
}
