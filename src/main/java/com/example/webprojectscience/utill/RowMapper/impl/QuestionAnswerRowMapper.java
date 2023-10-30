package com.example.webprojectscience.utill.RowMapper.impl;

import com.example.webprojectscience.models.QuestionAnswer;
import com.example.webprojectscience.utill.RowMapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionAnswerRowMapper implements RowMapper<QuestionAnswer> {
    @Override
    public QuestionAnswer from(ResultSet rs) throws SQLException {
        return new QuestionAnswer(
                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getLong("question_id"),
                rs.getString("text"),
                rs.getDate("date")
        );
    }
}
