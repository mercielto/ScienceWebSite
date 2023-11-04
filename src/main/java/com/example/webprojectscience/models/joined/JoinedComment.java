package com.example.webprojectscience.models.joined;

import com.example.webprojectscience.models.Comment;
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
public class JoinedComment {
    private Long id;
    private User user;
    private String text;
    private Date date;
    private Long answered;

    public JoinedComment(Comment comment, User user1) {
        id = comment.getId();
        user = user1;
        text = comment.getText();
        date = comment.getDate();
        answered = comment.getAnswered();
    }
}
