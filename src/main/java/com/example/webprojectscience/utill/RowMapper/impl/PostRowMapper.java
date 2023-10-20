package com.example.webprojectscience.utill.RowMapper.impl;

import com.example.webprojectscience.models.Post;
import com.example.webprojectscience.utill.RowMapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PostRowMapper implements RowMapper<Post> {
    @Override
    public Post from(ResultSet rs) throws SQLException {
        return new Post(
                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getString("path_in_storage"),
                rs.getLong("theme_id"),
                (List<String>) rs.getArray("tags"),
                rs.getDate("date")
        );
    }
}
