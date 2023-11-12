package com.example.webprojectscience.service;

import com.example.webprojectscience.models.User;
import com.example.webprojectscience.models.joined.JoinedAnswer;
import com.example.webprojectscience.models.joined.JoinedQuestion;
import com.example.webprojectscience.utill.DataBaseManager;

import java.util.List;

public class ForumAnswerHandlerService {
    public static JoinedQuestion getJoinedQuestionByLink(String link) {
        return DataBaseManager.getQuestionDao().getJoinedQuestionByLink(link);
    }

    public static List<JoinedAnswer> getJoinedAnswersByQuestionId(Long id) {
        return DataBaseManager.getQuestionAnswerDao().getJoinedAnswersByQuestionId(id);
    }

    public static User getUserById(Long id) {
        return DataBaseManager.getUserDao().getById(id);
    }
}
