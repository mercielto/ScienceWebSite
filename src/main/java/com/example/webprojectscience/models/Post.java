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
public class Post implements HasId{
    private Long id;
    private Long userId;
    private String pathInStorage;
    private Long themeId;
    private List<String> tags;
    private Date date;
}
