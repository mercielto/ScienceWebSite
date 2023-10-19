package com.example.webprojectscience.utill.RowMapper.impl;

import com.example.webprojectscience.models.Friend;
import com.example.webprojectscience.utill.RowMapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FriendRowMapper implements RowMapper<Friend> {
    @Override
    public Friend from(ResultSet rs, int rowNum) throws SQLException {
        return new Friend(
                rs.getLong("id"),
                rs.getLong("first_user_id"),
                rs.getLong("second_user_id")
        );
    }
}
