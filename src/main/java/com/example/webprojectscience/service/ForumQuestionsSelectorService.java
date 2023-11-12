package com.example.webprojectscience.service;

import com.example.webprojectscience.models.joined.JoinedQuestion;
import com.example.webprojectscience.models.Theme;
import com.example.webprojectscience.utill.DataBaseManager;
import com.example.webprojectscience.utill.PreparedStatementConditionBuilder;

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
}
