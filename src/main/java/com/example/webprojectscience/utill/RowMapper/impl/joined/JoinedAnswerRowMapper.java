package com.example.webprojectscience.utill.RowMapper.impl.joined;

import com.example.webprojectscience.models.User;
import com.example.webprojectscience.models.joined.JoinedAnswer;
import com.example.webprojectscience.utill.RowMapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JoinedAnswerRowMapper implements RowMapper<JoinedAnswer> {
    @Override
    public JoinedAnswer from(ResultSet rs) throws SQLException {
        return new JoinedAnswer(
                rs.getLong("answer_id"),
                new User(
                        rs.getLong("user_id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getBoolean("admin"),
                        rs.getString("name"),
                        rs.getString("link"),
                        rs.getString("profile_photo"),
                        rs.getString("description")
                ),
                rs.getLong("question_id"),
                rs.getString("text"),
                rs.getDate("date")
        );
    }
}
