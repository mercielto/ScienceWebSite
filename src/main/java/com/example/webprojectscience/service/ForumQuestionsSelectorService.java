package com.example.webprojectscience.service;

import com.example.webprojectscience.models.Question;
import com.example.webprojectscience.models.User;
import com.example.webprojectscience.models.joined.JoinedQuestion;
import com.example.webprojectscience.models.Theme;
import com.example.webprojectscience.utill.DataBaseManager;
import com.example.webprojectscience.utill.Generator;
import com.example.webprojectscience.utill.PreparedStatementConditionBuilder;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ForumQuestionsSelectorService {
    public static List<JoinedQuestion> getJoinedQuestions(Date from, Date to, Long themeId, List<String> tags, int count) {
        PreparedStatementConditionBuilder builder = new PreparedStatementConditionBuilder();
        List<Object> values = new ArrayList<>();
        if (from == null) {
            from = java.sql.Date.valueOf(LocalDate.of(1000, Month.APRIL, 3));
        }

        builder.moreEquals("date");
        values.add(from);

        if (to == null) {
            to = java.sql.Date.valueOf(LocalDate.now());
        }

        builder.lessEquals("date");
        values.add(to);

        if (themeId != null) {
            builder.equals("theme_id");
            values.add(themeId);
        }

        if (tags.size() != 0) {
            builder.contains("tags");
            values.add(tags.toString());
        }
        return DataBaseManager.getQuestionDao().getJoinedQuestions(builder, values);
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

    public static List<JoinedQuestion> getJoinedQuestionsByUserId(Long userId) {
        return DataBaseManager.getQuestionDao().getJoinedQuestionsByUserId(userId);
    }

    public static void createNewQuestion(HttpServletRequest req) {
        String tagsField = req.getParameter("tags");
        String[] tags;
        if (tagsField == null) {
            tags = new String[0];
        } else {
            tags = tagsField.split(" ");
        }

        Theme theme = DataBaseManager.getThemeDao().getByName(req.getParameter("theme"));
        User user = AuthorizationService.getAuthorizedUser(req);

        Question question = new Question();
        question.setDate(java.sql.Date.valueOf(LocalDate.now()));
        question.setMainQuestion(req.getParameter("title"));
        question.setText(req.getParameter("text"));
        question.setTags(List.of(tags));
        question.setThemeId(theme.getId());
        question.setUserId(user.getId());
        question.setLink(Generator.generateLink(user));
        question.setAnswerId(null);

        DataBaseManager.getQuestionDao().insert(question);
    }
}
