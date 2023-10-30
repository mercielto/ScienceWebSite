package com.example.webprojectscience.utill.RowMapper.impl;

import com.example.webprojectscience.models.Question;
import com.example.webprojectscience.utill.RowMapper.RowMapper;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class QuestionRowMapper implements RowMapper<Question> {
    @Override
    public Question from(ResultSet rs) throws SQLException {
        Array array = rs.getArray("tags");
        String[] tags;
        if (array != null) {
            tags = (String[]) array.getArray();
        } else {
            tags = new String[]{};
        }
        return new Question(
                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getDate("date"),
                rs.getString("text"),
                List.of(tags),
                rs.getLong("theme_id"),
                rs.getString("link"),
                rs.getString("main_question")
        );
    }
}
