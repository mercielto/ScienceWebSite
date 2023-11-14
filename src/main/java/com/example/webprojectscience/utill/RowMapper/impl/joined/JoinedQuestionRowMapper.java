package com.example.webprojectscience.utill.RowMapper.impl.joined;

import com.example.webprojectscience.models.Theme;
import com.example.webprojectscience.models.User;
import com.example.webprojectscience.models.joined.JoinedQuestion;
import com.example.webprojectscience.utill.DataBaseManager;
import com.example.webprojectscience.utill.RowMapper.RowMapper;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JoinedQuestionRowMapper implements RowMapper<JoinedQuestion> {
    @Override
    public JoinedQuestion from(ResultSet rs) throws SQLException {
        Array array = rs.getArray("tags");
        String[] tags;
        if (array != null) {
            tags = (String[]) array.getArray();
        } else {
            tags = new String[]{};
        }
        return new JoinedQuestion(
                rs.getLong("question_id_"),
                new User(
                    rs.getLong("user_id_"),
                    rs.getString("login"),
                    rs.getString("password"),
                    rs.getBoolean("admin"),
                    rs.getString("name"),
                    rs.getString("user_link_"),
                    rs.getString("profile_photo"),
                    rs.getString("description")
                ),
                rs.getDate("date"),
                rs.getString("text"),
                List.of(tags),
                new Theme(
                    rs.getLong("theme_id"),
                    rs.getString("theme_name_"),
                    rs.getString("picture_path")
                ),
                rs.getString("question_link_"),
                DataBaseManager.getQuestionAnswerDao().getByQuestionId(rs.getLong("question_id_")),
                rs.getString("main_question"),
                rs.getLong("answered_answer_id")
        );
    }
}
