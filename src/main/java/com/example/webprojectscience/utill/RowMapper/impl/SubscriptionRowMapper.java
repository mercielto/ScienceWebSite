package com.example.webprojectscience.utill.RowMapper.impl;

import com.example.webprojectscience.models.Subscription;
import com.example.webprojectscience.utill.RowMapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubscriptionRowMapper implements RowMapper<Subscription> {
    @Override
    public Subscription from(ResultSet rs, int rowNum) throws SQLException {
        return new Subscription(
                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getLong("subscribed_to_user_id")
        );
    }
}
