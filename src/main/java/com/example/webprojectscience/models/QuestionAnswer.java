package com.example.webprojectscience.models;

import com.example.webprojectscience.models.interfaces.HasId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAnswer implements HasId {
    private Long id;
    private Long userId;
    private Long questionId;
    private String text;
    private Date date;
}
