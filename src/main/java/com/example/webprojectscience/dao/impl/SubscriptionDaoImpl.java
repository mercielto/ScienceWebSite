package com.example.webprojectscience.dao.impl;

import com.example.webprojectscience.dao.extensions.SubscriptionDao;
import com.example.webprojectscience.models.Subscription;
import com.example.webprojectscience.utill.RowMapper.RowMapper;
import com.example.webprojectscience.utill.RowMapper.impl.SubscriptionRowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SubscriptionDaoImpl extends AbstractDAOImpl<Subscription> implements SubscriptionDao {
    public SubscriptionDaoImpl(Connection connection, String tableName, RowMapper<Subscription> rowMapper) {
        super(connection, tableName, rowMapper);

        SQL_INSERT = "INSERT INTO \"subscription\" (user_id, subscriber_id) VALUES (?, ?)";
        SQL_UPDATE = "UPDATE \"subscription\" SET user_id = ?, subscriber_id = ? WHERE id = ?";
    }


    @Override
    public List<Subscription> getSubscribers(Long subscriberId) {
        return getEntitiesByField("subscriber_id", subscriberId);
    }

    @Override
    public List<Subscription> getSubscriptions(Long userId) {
        return getEntitiesByField("user_id", userId);
    }

    @Override
    void fillGapsInStatement(PreparedStatement preparedStatement, Subscription entity) throws SQLException {
        preparedStatement.setLong(1, entity.getUserId());
        preparedStatement.setLong(2, entity.getSubscriberId());
    }
}
