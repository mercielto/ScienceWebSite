package com.example.webprojectscience.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements HasId{
    private Long id;
    private Long userId;
    private Long postId;
    private String text;
    private Date date;
    private Long answered;  // комментарий на комментарий Comment.id
}
