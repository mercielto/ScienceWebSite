package com.example.webprojectscience.dao.extensions;

import com.example.webprojectscience.dao.DAO;
import com.example.webprojectscience.models.User;

public interface UserDao extends DAO<User> {
    User getByLogin(String login);
    User getByLink(String link);
    boolean deleteByLogin(String login);
}
