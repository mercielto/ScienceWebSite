package com.example.webprojectscience.dao.impl;

import com.example.webprojectscience.dao.extensions.CommentDao;
import com.example.webprojectscience.models.Comment;
import com.example.webprojectscience.utill.RowMapper.RowMapper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CommentDaoImpl extends AbstractDAOImpl<Comment> implements CommentDao {
    public CommentDaoImpl(Connection connection, String tableName, RowMapper<Comment> rowMapper) {
        super(connection, tableName, rowMapper);

        SQL_INSERT = "INSERT INTO comment (user_id, post_id, text, date, answered) values (?, ?, ?, ?, ?)";
        SQL_UPDATE = "UPDATE comment set user_id = ?, post_id = ?, text = ?, date = ?, answered = ? where id = ?";
    }

    @Override
    public List<Comment> getByUserId(Long userId) {
        return getEntitiesByField("user_id", userId);
    }

    @Override
    public List<Comment> getByPostId(Long postId) {
        return getEntitiesByField("post_id", postId);
    }

    @Override
    void fillGapsInStatement(PreparedStatement preparedStatement, Comment entity) throws SQLException {
        preparedStatement.setLong(1, entity.getUserId());
        preparedStatement.setLong(2, entity.getPostId());
        preparedStatement.setString(3, entity.getText());
        preparedStatement.setDate(4, (Date) entity.getDate());
        preparedStatement.setLong(5, entity.getAnswered());
    }
}
