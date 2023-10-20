package com.example.webprojectscience.utill.RowMapper.impl;

import com.example.webprojectscience.models.Like;
import com.example.webprojectscience.utill.RowMapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LikeRowMapper implements RowMapper<Like> {
    @Override
    public Like from(ResultSet rs) throws SQLException {
        return new Like(
                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getLong("post_id")
        );
    }
}
