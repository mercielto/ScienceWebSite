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
public class Like implements HasId {
    private Long id;
    private Long userId;
    private Long postId;
}
