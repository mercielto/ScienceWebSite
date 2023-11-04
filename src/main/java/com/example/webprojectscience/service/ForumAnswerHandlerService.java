package com.example.webprojectscience.service;

import com.example.webprojectscience.models.Question;
import com.example.webprojectscience.models.QuestionAnswer;
import com.example.webprojectscience.models.User;
import com.example.webprojectscience.models.joined.JoinedAnswer;
import com.example.webprojectscience.models.joined.JoinedQuestion;
import com.example.webprojectscience.utill.DataBaseManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ForumAnswerHandlerService {
    public static JoinedQuestion getJoinedQuestionByLink(String link) {
        Question question = DataBaseManager.getQuestionDao().getByLink(link);
        return new JoinedQuestion(
                question,
                DataBaseManager.getUserDao().getById(question.getUserId()),
                new ArrayList<>(),
                DataBaseManager.getThemeDao().getById(question.getThemeId())
        );
    }

    public static List<JoinedAnswer> getJoinedAnswersByQuestionId(Long id) {
        List<JoinedAnswer> joinedQuestions = new ArrayList<>();
        List<QuestionAnswer> questionAnswers = DataBaseManager.getQuestionAnswerDao().getByQuestionId(id);

        for (QuestionAnswer questionAnswer : questionAnswers) {
            User user = DataBaseManager.getUserDao().getById(questionAnswer.getUserId());
            joinedQuestions.add(
                    new JoinedAnswer(questionAnswer, user)
            );
        }

        return joinedQuestions;
    }

    public static User getUserById(Long id) {
        return DataBaseManager.getUserDao().getById(id);
    }
}
