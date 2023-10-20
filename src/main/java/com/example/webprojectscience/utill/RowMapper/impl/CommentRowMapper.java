package com.example.webprojectscience.utill.RowMapper.impl;

import com.example.webprojectscience.models.Comment;
import com.example.webprojectscience.utill.RowMapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentRowMapper implements RowMapper<Comment> {
    @Override
    public Comment from(ResultSet rs) throws SQLException {
        return new Comment(
                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getLong("post_id"),
                rs.getString("text"),
                rs.getDate("date"),
                rs.getLong("answered")
        );
    }
}
