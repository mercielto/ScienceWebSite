package com.example.webprojectscience.dao.impl;

import com.example.webprojectscience.dao.extensions.UserDao;
import com.example.webprojectscience.models.User;
import com.example.webprojectscience.utill.RowMapper.RowMapper;
import com.example.webprojectscience.utill.RowMapper.impl.UserRowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAOImpl extends AbstractDAOImpl<User> implements UserDao {
    public UserDAOImpl(Connection connection, String tableName, RowMapper<User> rowMapper) {
        super(connection, tableName, rowMapper);

        SQL_INSERT = "INSERT INTO \"User\"" +
                " (login, password, name, link, profile_photo_path, description, admin)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?)";
        SQL_UPDATE = "UPDATE \"User\" SET " +
                "login = ?, password = ?, name = ?, link = ?, profile_photo_path = ?, description = ?, admin = ?" +
                " WHERE id = ? RETURNING *";
    }

    @Override
    protected void fillGapsInStatement(PreparedStatement preparedStatement, User entity) throws SQLException {
        preparedStatement.setString(1, entity.getLogin());
        preparedStatement.setString(2, entity.getPassword());
        preparedStatement.setString(3, entity.getName());
        preparedStatement.setString(4, entity.getLink());
        preparedStatement.setString(5, entity.getProfilePhotoPath());
        preparedStatement.setString(6, entity.getDescription());
        preparedStatement.setBoolean(7, entity.isAdmin());
    }


    @Override
    public User getByLogin(String login) {
        return getByField("login", login);
    }

    @Override
    public boolean deleteByLogin(String login) {
        return deleteByField("login", login);
    }
}
