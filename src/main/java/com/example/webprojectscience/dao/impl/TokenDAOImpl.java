package com.example.webprojectscience.dao.impl;

import com.example.webprojectscience.dao.extensions.TokenDao;
import com.example.webprojectscience.models.Token;
import com.example.webprojectscience.utill.RowMapper.impl.TokenRowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TokenDAOImpl extends AbstractDAOImpl<Token> implements TokenDao {
    protected String SQL_INSERT = "INSERT INTO \"Token\" (token, user_id, ip_address)" +
            " VALUES (?, ?, ?)";
    protected String SQL_UPDATE = "UPDATE \"Token\" SET" +
            "token = ?, ip_address = ? WHERE id = ?";


    public TokenDAOImpl(Connection connection) {
        super(connection);
        setTableName("Token");
        rowMapper = new TokenRowMapper();
    }

    @Override
    public Token update(Token entity) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, entity.getValue());
            preparedStatement.setString(2, entity.getIpAddress());
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
    public boolean insert(Token entity) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, entity.getValue());
            preparedStatement.setLong(2, entity.getUserId());
            preparedStatement.setString(3, entity.getIpAddress());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Token getByValue(String value) {
        return getByField("token", value);
    }

    @Override
    public Token getByUserId(Long id) {
        return getByField("user_id", id);
    }

    @Override
    public Token getByIpAddress(String address) {
        return null;
    }

    @Override
    public boolean deleteByValue(String value) {
        return deleteByField("token", value);
    }

    @Override
    public boolean deleteByUserID(Long id) {
        return deleteByField("id", id);
    }

    @Override
    public boolean deleteByIpAddress(String address) {
        return deleteByField("ip_address", address);
    }

}
