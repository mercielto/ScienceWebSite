package com.example.webprojectscience.models.joined;

import com.example.webprojectscience.models.Question;
import com.example.webprojectscience.models.QuestionAnswer;
import com.example.webprojectscience.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JoinedAnswer {
    private Long id;
    private User user;
    private Long questionId;
    private String text;
    private Date date;

    public JoinedAnswer(QuestionAnswer answer, User us) {
        id = answer.getId();
        user = us;
        questionId = answer.getQuestionId();
        text = answer.getText();
        date = answer.getDate();
    }
}
