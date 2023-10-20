package com.example.webprojectscience.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements HasId {
    private Long id;
    private String login;
    private String password;
    private boolean admin = false;
    private String name;
    private String link;
    private String profilePhotoPath;    // ссылка на фото в хранилище
    private String description;
}
