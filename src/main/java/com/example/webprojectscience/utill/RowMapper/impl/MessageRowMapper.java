package com.example.webprojectscience.utill.RowMapper.impl;

import com.example.webprojectscience.models.Message;
import com.example.webprojectscience.utill.RowMapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageRowMapper implements RowMapper<Message> {
    @Override
    public Message from(ResultSet rs, int rowNum) throws SQLException {
        return new Message(
                rs.getLong("id"),
                rs.getLong("from_user_id"),
                rs.getLong("to_user_id"),
                rs.getString("text"),
                rs.getDate("date")
        );
    }
}
