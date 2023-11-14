package com.example.webprojectscience.dao.impl;

import com.example.webprojectscience.dao.extensions.SubscriptionDao;
import com.example.webprojectscience.models.Subscription;
import com.example.webprojectscience.models.joined.JoinedSubscription;
import com.example.webprojectscience.utill.PreparedStatementConditionBuilder;
import com.example.webprojectscience.utill.RowMapper.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SubscriptionDaoImpl extends AbstractDAOImpl<Subscription> implements SubscriptionDao {
    public final String SQL_GET_JOINED = "SELECT sb.id \"sb_id_\", sb.user_id \"user_id\", " +
            "sb.subscriber_id \"subscriber_id\", us1.login \"us1_login_\", us1.password \"us1_password_\"," +
            " us1.admin \"us1_admin_\", us1.name \"us1_name_\", us1.link \"us1_link_\"," +
            " us1.profile_photo \"us1_profile_photo_\", us1.description \"us1_description_\"," +
            " us2.login \"us2_login_\", us2.password \"us2_password_\", us2.admin \"us2_admin_\"," +
            " us2.name \"us2_name_\", us2.link \"us2_link_\", us2.profile_photo \"us2_profile_photo_\"," +
            " us2.description \"us2_description_\" FROM subscription as sb, \"User\" as us1, \"User\" us2 " +
            "WHERE us1.id = sb.subscriber_id AND us2.id = sb.user_id";
    private RowMapper<JoinedSubscription> joinedSubscriptionRowMapper;
    public SubscriptionDaoImpl(Connection connection, String tableName, RowMapper<Subscription> rowMapper,
                               RowMapper<JoinedSubscription> jrm) {
        super(connection, tableName, rowMapper);
        joinedSubscriptionRowMapper = jrm;
        SQL_INSERT = "INSERT INTO \"subscription\" (user_id, subscriber_id) VALUES (?, ?)";
        SQL_UPDATE = "UPDATE \"subscription\" SET user_id = ?, subscriber_id = ? WHERE id = ?";
    }


    @Override
    public List<Subscription> getSubscribers(Long subscriberId) {
        return getEntitiesByEqualsField("user_id", subscriberId);
    }

    @Override
    public boolean isSubscribed(Long userId, Long subscribedToId) {
        return getSubscription(userId, subscribedToId) != null;
    }

    @Override
    public Subscription getSubscription(Long userId, Long subscribedToId) {
        PreparedStatementConditionBuilder builder = new PreparedStatementConditionBuilder(SQL_GET);
        builder.equals("user_id");
        builder.equals("subscriber_id");

        PreparedStatement preparedStatement = getPreparedStatement(builder.get(), List.of(subscribedToId, userId));
        List<Subscription> objects = (List<Subscription>) executeSqlPreparedStatement(preparedStatement, rowMapper);
        if (objects.size() == 0) {
            return null;
        }
        return objects.get(0);
    }

    @Override
    public List<JoinedSubscription> getJoinedSubscribers(Long userId) {
        return (List<JoinedSubscription>) getListByEqualsField(SQL_GET_JOINED,
                "sb.user_id", userId, joinedSubscriptionRowMapper);
    }

    @Override
    public List<JoinedSubscription> getJoinedSubscriptions(Long userId) {
        return (List<JoinedSubscription>) getListByEqualsField(SQL_GET_JOINED,
                "sb.subscriber_id", userId, joinedSubscriptionRowMapper);
    }


    @Override
    public List<Subscription> getSubscriptions(Long userId) {
        return getEntitiesByEqualsField("subscriber_id", userId);
    }

    @Override
    void fillGapsInStatement(PreparedStatement preparedStatement, Subscription entity) throws SQLException {
        preparedStatement.setLong(1, entity.getUserId());
        preparedStatement.setLong(2, entity.getSubscriberId());
    }
}
