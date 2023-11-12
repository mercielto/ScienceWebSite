package com.example.webprojectscience.models.joined;

import com.example.webprojectscience.models.Question;
import com.example.webprojectscience.models.QuestionAnswer;
import com.example.webprojectscience.models.Theme;
import com.example.webprojectscience.models.User;
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
    private Theme theme;
    private String link;
    private List<QuestionAnswer> questionAnswers;
    private String mainQuestion;
    private Long answerId;

    public JoinedQuestion(Question question, User user, List<QuestionAnswer> q, Theme theme1) {
        id = question.getId();
        this.user = user;
        date = question.getDate();
        text = question.getText();
        tags = question.getTags();
        theme = theme1;
        link = question.getLink();
        questionAnswers = q;
        mainQuestion = question.getMainQuestion();
    }
}
