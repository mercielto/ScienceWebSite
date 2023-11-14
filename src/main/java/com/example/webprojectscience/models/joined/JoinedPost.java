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
}
