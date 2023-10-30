package com.example.webprojectscience.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JoinedQuestion {
    private Long id;
    private User user;
    private Date date;
    private String text;
    private List<String> tags;
    private Long themeId;
    private String link;
    private List<QuestionAnswer> questionAnswers;
    private String mainQuestion;

    public JoinedQuestion(Question question, User user, List<QuestionAnswer> q) {
        id = question.getId();
        this.user = user;
        date = question.getDate();
        text = question.getText();
        tags = question.getTags();
        themeId = question.getThemeId();
        link = question.getLink();
        questionAnswers = q;
        mainQuestion = question.getMainQuestion();
    }
}
