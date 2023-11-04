package com.example.webprojectscience.service;

import com.example.webprojectscience.models.joined.JoinedQuestion;
import com.example.webprojectscience.models.Question;
import com.example.webprojectscience.models.Theme;
import com.example.webprojectscience.utill.DataBaseManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ForumQuestionsSelectorService {
    private static void removeByThemeId(List<Question> questions, Long themeId) {
        for (Question question : questions) {
            if (!Objects.equals(question.getThemeId(), themeId)) {
                questions.remove(question);
            }
        }
    }
    public static List<JoinedQuestion> getJoinedQuestions(Date from, Date to, Long themeId, List<String> tags, int count) {
        List<Question> questions = DataBaseManager.getQuestionDao().getByDate(from, to);

        if (themeId != null) {
            removeByThemeId(questions, themeId);
        }

        if (tags.size() != 0) {
            removeByTags(questions, tags);
        }

        List<JoinedQuestion> joinedQuestions = new ArrayList<>();
        for (Question question : questions) {
            joinedQuestions.add(new JoinedQuestion(
                    question,
                    DataBaseManager.getUserDao().getById(question.getUserId()),
                    DataBaseManager.getQuestionAnswerDao().getByQuestionId(question.getId()),
                    DataBaseManager.getThemeDao().getById(question.getThemeId())
            ));
        }
        if (count > joinedQuestions.size()) {
            count = joinedQuestions.size();
        }
        return joinedQuestions.subList(0, count);
    }

    private static void removeByTags(List<Question> questions, List<String> tags) {
        for (Question question : questions) {
            if (!question.getTags().contains(tags)) {
                questions.remove(question);
            }
        }
    }

    public static List<Integer> getPageNumbers(int max) {
        List<Integer> pageNumbers = new ArrayList<>();
        for (int i = 0; i * 10 < max; i++) {
            pageNumbers.add(i + 1);
        }

        return pageNumbers;
    }

    public static List<Theme> getThemes() {
        return DataBaseManager.getThemeDao().getAll();
    }
}
