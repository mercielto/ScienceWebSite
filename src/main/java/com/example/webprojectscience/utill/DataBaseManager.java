package com.example.webprojectscience.utill;

import com.example.webprojectscience.dao.extensions.TokenDao;
import com.example.webprojectscience.dao.extensions.UserDao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataBaseManager {
    private static UserDao userDao;
    private static TokenDao tokenDao;
    public static void setUserDao(UserDao dao) {
        userDao = dao;
    }

    public static UserDao getUserDao() {
        return userDao;
    }

    public static void setTokenDao(TokenDao tokenDao) {
        DataBaseManager.tokenDao = tokenDao;
    }

    public static TokenDao getTokenDao() {
        return tokenDao;
    }
}
