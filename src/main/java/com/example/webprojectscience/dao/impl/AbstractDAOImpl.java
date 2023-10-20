package com.example.webprojectscience.dao.impl;

import com.example.webprojectscience.dao.DAO;
import com.example.webprojectscience.models.HasId;
import com.example.webprojectscience.utill.RowMapper.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

abstract class AbstractDAOImpl<T extends HasId> implements DAO<T> {
    protected Connection connection;
    protected RowMapper<T> rowMapper;

    protected String SQL_GET_ALL;
    protected String SQL_GET;
    protected String SQL_DELETE;
    protected String SQL_UPDATE;
    protected String SQL_INSERT;

    public AbstractDAOImpl(Connection connection, String tableName, RowMapper<T> rowMapper) {
        this.connection = connection;
        setTableName(tableName);
        this.rowMapper = rowMapper;
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

            while (resultSet.next()) {
                entities.add(rowMapper.from(resultSet));
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

            return rowMapper.from(resultSet);
        } catch (SQLException e) {
            return null;
        }
    }

    protected List<T> getEntitiesByField(String fieldName, Object value) {
        List<T> entities = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET.formatted(fieldName));
            preparedStatement.setObject(1, value);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            while (resultSet.next()) {
                entities.add(rowMapper.from(resultSet));
            }
        } catch (SQLException e) {
            return entities;
        }
        return entities;
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

    public T update(T entity) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
            fillGapsInStatement(preparedStatement, entity);

            // ?
            preparedStatement = connection.prepareStatement(preparedStatement.toString());
            preparedStatement.setLong(1, entity.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            if (resultSet.wasNull()) {
                return null;
            }
            return rowMapper.from(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // заполнение всех ? кроме id
    abstract void fillGapsInStatement(PreparedStatement preparedStatement, T entity) throws SQLException;

    public boolean insert(T entity) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);
            fillGapsInStatement(preparedStatement, entity);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
