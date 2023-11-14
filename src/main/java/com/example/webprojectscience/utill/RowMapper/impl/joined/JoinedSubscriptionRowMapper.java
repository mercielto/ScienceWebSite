package com.example.webprojectscience.utill.RowMapper.impl.joined;

import com.example.webprojectscience.models.User;
import com.example.webprojectscience.models.joined.JoinedSubscription;
import com.example.webprojectscience.utill.RowMapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JoinedSubscriptionRowMapper implements RowMapper<JoinedSubscription> {
    @Override
    public JoinedSubscription from(ResultSet rs) throws SQLException {
        return new JoinedSubscription(
                rs.getLong("sb_id_"),
                new User(
                        rs.getLong("user_id"),
                        rs.getString("us2_login_"),
                        rs.getString("us2_password_"),
                        rs.getBoolean("us2_admin_"),
                        rs.getString("us2_name_"),
                        rs.getString("us2_link_"),
                        rs.getString("us2_profile_photo_"),
                        rs.getString("us2_description_")
                ),
                new User(
                        rs.getLong("subscriber_id"),
                        rs.getString("us1_login_"),
                        rs.getString("us1_password_"),
                        rs.getBoolean("us1_admin_"),
                        rs.getString("us1_name_"),
                        rs.getString("us1_link_"),
                        rs.getString("us1_profile_photo_"),
                        rs.getString("us1_description_")
                )
        );
    }
}
