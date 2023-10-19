package com.example.webprojectscience.dao.impl;

import com.example.webprojectscience.dao.extensions.UserDao;
import com.example.webprojectscience.models.User;
import com.example.webprojectscience.utill.RowMapper.impl.UserRowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl extends AbstractDAOImpl<User> implements UserDao {
    protected String SQL_INSERT = "INSERT INTO \"User\" (login, password) VALUES (?, ?)";
    protected String SQL_UPDATE = "UPDATE \"User\" SET " +
            "login = ?, password = ? WHERE id = ? RETURNING *";
    public UserDAOImpl(Connection connection) {
        super(connection);
        setTableName("User");
        rowMapper = new UserRowMapper();
    }
    @Override
    public User update(User entity) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setLong(3, entity.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            if (resultSet.wasNull()) {
                return null;
            }
            return rowMapper.from(resultSet, 1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean insert(User entity) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
