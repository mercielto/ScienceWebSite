package com.example.webprojectscience.utill.RowMapper.impl.joined;

import com.example.webprojectscience.models.Post;
import com.example.webprojectscience.models.Theme;
import com.example.webprojectscience.models.User;
import com.example.webprojectscience.models.joined.JoinedPost;
import com.example.webprojectscience.utill.DataBaseManager;
import com.example.webprojectscience.utill.RowMapper.RowMapper;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JoinedPostRowMapper implements RowMapper<JoinedPost> {
    @Override
    public JoinedPost from(ResultSet rs) throws SQLException {
        Array array = rs.getArray("tags");
        String[] tags;
        if (array != null) {
            tags = (String[]) array.getArray();
        } else {
            tags = new String[]{};
        }
        return new JoinedPost(
                rs.getLong("post_id"),
                new User(
                    rs.getLong("user_id"),
                    rs.getString("login"),
                    rs.getString("password"),
                    rs.getBoolean("admin"),
                    rs.getString("user_name_"),
                    rs.getString("user_link_"),
                    rs.getString("profile_photo"),
                    rs.getString("description")
                ),
                rs.getString("path_in_storage"),
                new Theme(
                    rs.getLong("theme_id"),
                    rs.getString("theme_name_"),
                    rs.getString("picture_path")
                ),
                List.of(tags),
                rs.getDate("date"),
                rs.getString("title"),
                DataBaseManager.getCommentDao().getJoinedByPostId(rs.getLong("post_id")),
                DataBaseManager.getLikeDao().getJoinedByPostId(rs.getLong("post_id")),
                rs.getString("post_link_")
        );
    }
}
