package com.example.webprojectscience.models.joined;

import com.example.webprojectscience.models.Comment;
import com.example.webprojectscience.models.Post;
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
public class JoinedComment {
    private Long id;
    private User user;
    private Post post;
    private String text;
    private Date date;
}
