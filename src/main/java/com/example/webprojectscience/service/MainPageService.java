package com.example.webprojectscience.service;

import com.example.webprojectscience.utill.DataBaseManager;

public class MainPageService {
    public static int getUsersCount() {
        return DataBaseManager.getUserDao().getAll().size();
    }

    public static int getOnlineUserCount() {
        return DataBaseManager.getTokenDao().getBySession(true).size();
    }

    public static int getPostsCount() {
        return DataBaseManager.getPostDao().getAll().size();
    }

    public static int getAnsweredQuestionsCount() {
        return DataBaseManager.getQuestionDao().getAllAnswered().size();
    }
}
