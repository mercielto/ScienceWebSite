package com.example.webprojectscience.models.joined;

import com.example.webprojectscience.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JoinedSubscription {
    private Long id;
    private User user;
    private User subscriber;
}
