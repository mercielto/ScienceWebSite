package com.example.webprojectscience.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Token implements HasId{
    private Long id;
    private String value;
    private Long userId;
    private String ipAddress;
    private Boolean session;
}
