package com.example.webprojectscience.dao.impl;

import com.example.webprojectscience.dao.extensions.LikeDao;
import com.example.webprojectscience.models.Like;
import com.example.webprojectscience.utill.RowMapper.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LikeDaoImpl extends AbstractDAOImpl<Like> implements LikeDao {
    public LikeDaoImpl(Connection connection, String tableName, RowMapper<Like> rowMapper) {
        super(connection, tableName, rowMapper);

        SQL_INSERT = "INSERT INTO \"Like\" (user_id, post_id) values (?, ?)";
        SQL_UPDATE = "UPDATE \"Like\" set user_id = ?, post_id = ? where id = ?";
    }

    @Override
    void fillGapsInStatement(PreparedStatement preparedStatement, Like entity) throws SQLException {
        preparedStatement.setLong(1, entity.getUserId());
        preparedStatement.setLong(2, entity.getPostId());
    }
}
