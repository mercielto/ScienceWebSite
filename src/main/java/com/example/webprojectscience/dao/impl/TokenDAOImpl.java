package com.example.webprojectscience.dao.impl;

import com.example.webprojectscience.dao.extensions.TokenDao;
import com.example.webprojectscience.models.Token;
import com.example.webprojectscience.utill.RowMapper.RowMapper;
import com.example.webprojectscience.utill.RowMapper.impl.TokenRowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TokenDAOImpl extends AbstractDAOImpl<Token> implements TokenDao {

    public TokenDAOImpl(Connection connection, String tableName, RowMapper<Token> rowMapper) {
        super(connection, tableName, rowMapper);

        SQL_INSERT = "INSERT INTO \"token\" (value, user_id, ip_address)" +
                " VALUES (?, ?, ?)";
        SQL_UPDATE = "UPDATE \"token\" SET value = ?, user_id = ?, ip_address = ? WHERE id = ?";
    }

    @Override
    void fillGapsInStatement(PreparedStatement preparedStatement, Token entity) throws SQLException {
        preparedStatement.setString(1, entity.getValue());
        preparedStatement.setLong(2, entity.getUserId());
        preparedStatement.setString(3, entity.getIpAddress());
    }

    @Override
    public Token getByValue(String value) {
        return getByField("value", value);
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
