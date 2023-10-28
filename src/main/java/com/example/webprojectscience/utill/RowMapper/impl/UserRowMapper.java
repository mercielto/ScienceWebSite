package com.example.webprojectscience.utill.RowMapper.impl;

import com.example.webprojectscience.models.User;
import com.example.webprojectscience.utill.RowMapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User from(ResultSet rs) throws SQLException {
        return new User(
                rs.getLong("id"),
                rs.getString("login"),
                rs.getString("password"),
                rs.getBoolean("admin"),
                rs.getString("name"),
                rs.getString("link"),
                rs.getString("profile_photo"),
                rs.getString("description")
        );
    }
}
