package com.example.webprojectscience.utill.RowMapper.impl.joined;

import com.example.webprojectscience.models.Post;
import com.example.webprojectscience.models.User;
import com.example.webprojectscience.models.joined.JoinedLike;
import com.example.webprojectscience.utill.RowMapper.RowMapper;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JoinedLikeRowMapper implements RowMapper<JoinedLike> {
    @Override
    public JoinedLike from(ResultSet rs) throws SQLException {
        Array array = rs.getArray("tags");
        String[] tags;
        if (array != null) {
            tags = (String[]) array.getArray();
        } else {
            tags = new String[]{};
        }
        return new JoinedLike(
                rs.getLong("like_id_"),
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
                        rs.getDate("date"),
                        rs.getString("title"),
                        rs.getString("post_link_")
                )
        );
    }
}
