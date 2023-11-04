package com.example.webprojectscience.models.joined;

import com.example.webprojectscience.models.*;
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
public class JoinedPost {
    private Long id;
    private User user;
    private String pathInStorage;
    private Theme theme;
    private List<String> tags;
    private Date date;
    private String title;
    private List<JoinedComment> comments;
    private List<JoinedLike> likes;
    private String link;

    public JoinedPost(Post post, User user1, Theme theme1,
                      List<JoinedComment> comments1, List<JoinedLike> likes1) {
        id = post.getId();
        user = user1;
        pathInStorage = post.getPathInStorage();
        theme = theme1;
        tags = post.getTags();
        date = post.getDate();
        title = post.getTitle();
        comments = comments1;
        likes = likes1;
        link = post.getLink();
    }
}
