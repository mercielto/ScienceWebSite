package com.example.webprojectscience.utill;

import com.example.webprojectscience.dao.extensions.*;
import com.example.webprojectscience.models.Message;
import lombok.Getter;
import lombok.Setter;

public class DataBaseManager {
    private static UserDao userDao;
    private static TokenDao tokenDao;
    private static CommentDao commentDao;
    private static FriendDao friendDao;
    private static LikeDao likeDao;
    private static PostDao postDao;
    private static SubscriptionDao subscriptionDao;
    private static ThemeDao themeDao;
    private static QuestionDao questionDao;
    private static QuestionAnswerDao questionAnswerDao;
//    private static MessageDao
    public static void setUserDao(UserDao dao) {
        userDao = dao;
    }

    public static UserDao getUserDao() {
        return userDao;
    }

    public static void setTokenDao(TokenDao tokenDao) {
        DataBaseManager.tokenDao = tokenDao;
    }

    public static TokenDao getTokenDao() {
        return tokenDao;
    }

    public static CommentDao getCommentDao() {
        return commentDao;
    }

    public static void setCommentDao(CommentDao commentDao) {
        DataBaseManager.commentDao = commentDao;
    }

    public static FriendDao getFriendDao() {
        return friendDao;
    }

    public static void setFriendDao(FriendDao friendDao) {
        DataBaseManager.friendDao = friendDao;
    }

    public static LikeDao getLikeDao() {
        return likeDao;
    }

    public static void setLikeDao(LikeDao likeDao) {
        DataBaseManager.likeDao = likeDao;
    }

    public static PostDao getPostDao() {
        return postDao;
    }

    public static void setPostDao(PostDao postDao) {
        DataBaseManager.postDao = postDao;
    }

    public static SubscriptionDao getSubscriptionDao() {
        return subscriptionDao;
    }

    public static void setSubscriptionDao(SubscriptionDao subscriptionDao) {
        DataBaseManager.subscriptionDao = subscriptionDao;
    }

    public static ThemeDao getThemeDao() {
        return themeDao;
    }

    public static void setThemeDao(ThemeDao themeDao) {
        DataBaseManager.themeDao = themeDao;
    }

    public static QuestionDao getQuestionDao() {
        return questionDao;
    }

    public static void setQuestionDao(QuestionDao questionDao) {
        DataBaseManager.questionDao = questionDao;
    }

    public static QuestionAnswerDao getQuestionAnswerDao() {
        return questionAnswerDao;
    }

    public static void setQuestionAnswerDao(QuestionAnswerDao questionAnswerDao) {
        DataBaseManager.questionAnswerDao = questionAnswerDao;
    }
}
