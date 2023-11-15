package com.example.webprojectscience.models;

import com.example.webprojectscience.models.interfaces.HasId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Theme implements HasId {
    private Long id;
    private String name;
    private String picturePath;
}
