package com.example.webprojectscience.dao.impl;

import com.example.webprojectscience.dao.DAO;
import com.example.webprojectscience.models.HasId;
import com.example.webprojectscience.utill.PreparedStatementConditionBuilder;
import com.example.webprojectscience.utill.RowMapper.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

abstract class AbstractDAOImpl<T extends HasId> implements DAO<T> {
    protected Connection connection;
    protected RowMapper<T> rowMapper;

    public String SQL_GET;
    public String SQL_DELETE;
    public String SQL_UPDATE;
    public String SQL_INSERT;

    public AbstractDAOImpl(Connection connection, String tableName, RowMapper<T> rowMapper) {
        this.connection = connection;
        setTableName(tableName);
        this.rowMapper = rowMapper;
    }

    public void setTableName(String name) {
        SQL_GET = "SELECT * FROM \"%s\"".formatted(name);
        SQL_DELETE = "DELETE FROM \"%s\"".formatted(name);
    }

    @Override
    public List<T> getAll() {
        return (List<T>)(Object) executeSqlStatement(SQL_GET, rowMapper);
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
        PreparedStatementConditionBuilder builder = new PreparedStatementConditionBuilder(SQL_GET);
        builder.equals(fieldName);
        PreparedStatement preparedStatement = getPreparedStatement(builder.get(), List.of(value));
        List<Object> entities = executeSqlStatement(preparedStatement.toString(), rowMapper);
        if (entities.size() == 0) {
            return null;
        }
        return (T) entities.get(0);
    }

    protected List<T> getEntitiesByField(String fieldName, Object value) {
        PreparedStatementConditionBuilder builder = new PreparedStatementConditionBuilder(SQL_GET);
        builder.equals(fieldName);
        PreparedStatement preparedStatement = getPreparedStatement(builder.get(), List.of(value));
        return (List<T>) (Object) executeSqlPreparedStatement(preparedStatement, rowMapper);
    }

    protected PreparedStatement getPreparedStatement(String sql, List<Object> args) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int index = 1; index < args.size() + 1; index++) {
                preparedStatement.setObject(index, args.get(index - 1));
            }
            return preparedStatement;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected List<Object> executeSqlPreparedStatement(PreparedStatement preparedStatement, RowMapper rm) {
        List<Object> entities = new ArrayList<>();
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                entities.add(rm.from(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entities;
    }

    protected List<Object> executeSqlStatement(String sql, RowMapper rm) {
        List<Object> entities = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                entities.add(rm.from(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entities;
    }

    protected boolean deleteByField(String fieldName, Object value) {
        PreparedStatementConditionBuilder builder = new PreparedStatementConditionBuilder(SQL_DELETE);
        builder.equals(fieldName);

        try {
            PreparedStatement preparedStatement = getPreparedStatement(builder.get(), List.of(value));
            int n = preparedStatement.executeUpdate();

            if (n == 0) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(T entity) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
            fillGapsInStatement(preparedStatement, entity);

            preparedStatement = connection.prepareStatement(preparedStatement.toString());
            preparedStatement.setLong(1, entity.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // заполнение всех кроме id
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
