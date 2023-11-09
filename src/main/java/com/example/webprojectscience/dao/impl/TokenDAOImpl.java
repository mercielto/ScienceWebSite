package com.example.webprojectscience.dao.impl;

import com.example.webprojectscience.dao.extensions.TokenDao;
import com.example.webprojectscience.models.Token;
import com.example.webprojectscience.utill.RowMapper.RowMapper;
import com.example.webprojectscience.utill.RowMapper.impl.TokenRowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TokenDAOImpl extends AbstractDAOImpl<Token> implements TokenDao {

    public TokenDAOImpl(Connection connection, String tableName, RowMapper<Token> rowMapper) {
        super(connection, tableName, rowMapper);

        SQL_INSERT = "INSERT INTO \"token\" (value, user_id, ip_address, session)" +
                " VALUES (?, ?, ?, ?)";
        SQL_UPDATE = "UPDATE \"token\" SET value = ?, user_id = ?, ip_address = ?, session = ? WHERE id = ?";
    }

    @Override
    void fillGapsInStatement(PreparedStatement preparedStatement, Token entity) throws SQLException {
        preparedStatement.setString(1, entity.getValue());
        preparedStatement.setLong(2, entity.getUserId());
        preparedStatement.setString(3, entity.getIpAddress());
        preparedStatement.setBoolean(4, entity.getSession());
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
        return getByField("ip_address", address);
    }

    @Override
    public List<Token> getBySession(boolean session) {
        return getEntitiesByField("session", session);
    }

    @Override
    public boolean deleteByValue(String value) {
        return deleteByField("value", value);
    }

    @Override
    public boolean deleteByUserID(Long id) {
        return deleteByField("user_id", id);
    }

    @Override
    public boolean deleteByIpAddress(String address) {
        return deleteByField("ip_address", address);
    }

}
