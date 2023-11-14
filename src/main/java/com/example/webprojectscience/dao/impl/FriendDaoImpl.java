package com.example.webprojectscience.dao.impl;

import com.example.webprojectscience.dao.extensions.FriendDao;
import com.example.webprojectscience.models.Friend;
import com.example.webprojectscience.utill.RowMapper.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class FriendDaoImpl extends AbstractDAOImpl<Friend> implements FriendDao {
    public FriendDaoImpl(Connection connection, String tableName, RowMapper<Friend> rowMapper) {
        super(connection, tableName, rowMapper);

        SQL_INSERT = "INSERT INTO comment (first_user_id, second_user_id) values (?, ?)";
        SQL_UPDATE = "UPDATE comment set first_user_id = ?, second_user_id = ? where id = ?";
    }

    @Override
    public List<Friend> getFriends(Long userId) {
        List<Friend> friends1 = getEntitiesByEqualsField("first_user_id", userId);
        List<Friend> friends2 = getEntitiesByEqualsField("second_user_id", userId);

        for (Friend friend : friends2) {
            if (!friends1.contains(friend)) {
                friends1.add(friend);
            }
        }
        return friends1;
    }

    @Override
    void fillGapsInStatement(PreparedStatement preparedStatement, Friend entity) throws SQLException {
        preparedStatement.setLong(1, entity.getFirstUserId());
        preparedStatement.setLong(2, entity.getSecondUserId());
    }
}
