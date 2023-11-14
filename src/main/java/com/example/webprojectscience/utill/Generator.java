package com.example.webprojectscience.utill;

import com.example.webprojectscience.models.User;

import java.util.UUID;

public class Generator {
    public static String generateToken() {
        return UUID.randomUUID().toString();
    }

    public static String generateFilePostName(User user) {
        return user.getLogin() + System.currentTimeMillis() + ".txt";
    }

    public static String generatePostLink(User user) {
        return user.getId() + "" + System.currentTimeMillis();
    }
}
