package com.example.webprojectscience.models.joined;

import com.example.webprojectscience.models.Like;
import com.example.webprojectscience.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JoinedLike {
    private Long id;
    private User user;

    public JoinedLike(Like like, User user1) {
        id = like.getId();
        user = user1;
    }
}
