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
public class Question implements HasId {
    private Long id;
    private Long userId;
    private Date date;
    private String text;
    private List<String> tags;
    private Long themeId;
    private String link;
    private String mainQuestion;
    private Long answerId;
}
