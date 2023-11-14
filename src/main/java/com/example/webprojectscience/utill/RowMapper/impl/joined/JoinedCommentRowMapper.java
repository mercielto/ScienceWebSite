package com.example.webprojectscience.utill.RowMapper.impl.joined;

import com.example.webprojectscience.models.Post;
import com.example.webprojectscience.models.User;
import com.example.webprojectscience.models.joined.JoinedComment;
import com.example.webprojectscience.utill.RowMapper.RowMapper;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JoinedCommentRowMapper implements RowMapper<JoinedComment> {
    @Override
    public JoinedComment from(ResultSet rs) throws SQLException {
        Array array = rs.getArray("tags");
        String[] tags;
        if (array != null) {
            tags = (String[]) array.getArray();
        } else {
            tags = new String[]{};
        }
        return new JoinedComment(
                rs.getLong("comment_id_"),
                new User(
                        rs.getLong("user_id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getBoolean("admin"),
                        rs.getString("name"),
                        rs.getString("user_link_"),
                        rs.getString("profile_photo"),
                        rs.getString("description")
                ),
                new Post(
                        rs.getLong("post_id"),
                        rs.getLong("user_id"),
                        rs.getString("path_in_storage"),
                        rs.getLong("theme_id"),
                        List.of(tags),
                        rs.getDate("post_date_"),
                        rs.getString("title"),
                        rs.getString("post_link_")
                ),
                rs.getString("text"),
                rs.getDate("comment_date_")
        );
    }
}
