package com.example.webprojectscience.service;

import com.example.webprojectscience.models.User;
import com.example.webprojectscience.utill.DataBaseManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


public class RegistrationService {
    public static boolean registerUser(String login, String password) {
        User user = new User();
        try {
            user.setLogin(login);
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            user.setPassword(Arrays.toString(messageDigest.digest(password.getBytes())));
        } catch (NoSuchAlgorithmException e) {
            return false;
        }
        return DataBaseManager.getUserDao().insert(user);
    }
}
