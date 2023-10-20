package com.example.webprojectscience.service;

import com.example.webprojectscience.models.User;
import com.example.webprojectscience.utill.DataBaseManager;
import com.example.webprojectscience.utill.Encryption;


public class RegistrationService {
    public static boolean registerUser(String login, String password) {
        User user = new User();

        user.setLogin(login);
        String encrypted = Encryption.encrypt(password);

        if (encrypted == null) {
            return false;
        }

        user.setPassword(encrypted);
        user.setName(login);
        user.setLink(login);

        return DataBaseManager.getUserDao().insert(user);
    }
}
