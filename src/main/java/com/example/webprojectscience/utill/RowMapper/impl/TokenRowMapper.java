package com.example.webprojectscience.utill.RowMapper.impl;

import com.example.webprojectscience.models.Token;
import com.example.webprojectscience.utill.RowMapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TokenRowMapper implements RowMapper<Token> {
    @Override
    public Token from(ResultSet rs) throws SQLException {
        return new Token(
                rs.getLong("id"),
                rs.getString("token"),
                rs.getLong("user_id"),
                rs.getString("ip_address"));
    }
}
