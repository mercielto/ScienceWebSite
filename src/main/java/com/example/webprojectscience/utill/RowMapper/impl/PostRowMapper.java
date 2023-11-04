package com.example.webprojectscience.utill.RowMapper.impl;

import com.example.webprojectscience.models.Post;
import com.example.webprojectscience.utill.RowMapper.RowMapper;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PostRowMapper implements RowMapper<Post> {
    @Override
    public Post from(ResultSet rs) throws SQLException {
        Array array = rs.getArray("tags");
        String[] tags;
        if (array != null) {
            tags = (String[]) array.getArray();
        } else {
            tags = new String[]{};
        }
        return new Post(
                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getString("path_in_storage"),
                rs.getLong("theme_id"),
                List.of(tags),
                rs.getDate("date"),
                rs.getString("title"),
                rs.getString("link")
        );
    }
}
