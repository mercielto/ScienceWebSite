package com.example.webprojectscience.dao.impl;

import com.example.webprojectscience.dao.DAO;
import com.example.webprojectscience.utill.RowMapper.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

abstract class AbstractDAOImpl<T> implements DAO<T> {
    protected Connection connection;
    protected RowMapper<T> rowMapper;

    public String SQL_GET_ALL;
    public String SQL_GET;
    public String SQL_DELETE;

    public AbstractDAOImpl(Connection connection) {
        this.connection = connection;
    }

    public void setTableName(String name) {
        SQL_GET_ALL = "SELECT * FROM \"%s\"".formatted(name);
        SQL_GET = "SELECT * FROM \"%s\" WHERE %s = ?".formatted(name, "%s");
        SQL_DELETE = "DELETE FROM \"%s\" WHERE %s = ?".formatted(name, "%s");
    }

    @Override
    public List<T> getAll() {
        List<T> entities = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(SQL_GET_ALL);
            ResultSet resultSet = statement.getResultSet();

            int i = 1;
            while (resultSet.next()) {
                entities.add(rowMapper.from(resultSet, i));
                i++;
            }

            return entities;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public T getById(Long id) {
        return getByField("id", id);
    }

    @Override
    public boolean delete(Long id) {
        return deleteByField("id", id);
    }

    protected T getByField(String fieldName, Object value) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET.formatted(fieldName));
            preparedStatement.setObject(1, value);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            if (resultSet.wasNull()) {
                return null;
            }

            return rowMapper.from(resultSet, 1);
        } catch (SQLException e) {
            return null;
        }
    }

    protected boolean deleteByField(String fieldName, Object value) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE.formatted(fieldName));
            preparedStatement.setObject(1, value);
            int n = preparedStatement.executeUpdate();

            if (n == 0) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

}
