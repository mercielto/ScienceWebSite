package com.example.webprojectscience.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Like {
    private Long id;
    private Long userId;
    private Long postId;
}
