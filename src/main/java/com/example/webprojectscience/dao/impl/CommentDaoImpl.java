package com.example.webprojectscience.dao.impl;

import com.example.webprojectscience.dao.extensions.CommentDao;
import com.example.webprojectscience.models.Comment;
import com.example.webprojectscience.models.joined.JoinedComment;
import com.example.webprojectscience.utill.RowMapper.RowMapper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CommentDaoImpl extends AbstractDAOImpl<Comment> implements CommentDao {
    public final String SQL_GET_JOINED = "SELECT *, comment.id \"comment_id_\", comment.date \"comment_date_\"," +
            " us.link \"user_link_\", post.date \"post_date_\", post.link \"post_link_\" FROM comment," +
            " \"User\" as us, post WHERE comment.user_id = us.id AND comment.post_id = post.id";
    private RowMapper<JoinedComment> joinedCommentRowMapper;
    public CommentDaoImpl(Connection connection, String tableName, RowMapper<Comment> rowMapper,
                          RowMapper<JoinedComment> joinedRowMapper) {
        super(connection, tableName, rowMapper);
        joinedCommentRowMapper = joinedRowMapper;

        SQL_INSERT = "INSERT INTO comment (user_id, post_id, text, date) values (?, ?, ?, ?)";
        SQL_UPDATE = "UPDATE comment set user_id = ?, post_id = ?, text = ?, date = ? where id = ?";
    }

    @Override
    public List<Comment> getByUserId(Long userId) {
        return getEntitiesByEqualsField("user_id", userId);
    }

    @Override
    public List<Comment> getByPostId(Long postId) {
        return getEntitiesByEqualsField("post_id", postId);
    }

    @Override
    public List<JoinedComment> getJoinedByPostId(long postId) {
        return (List<JoinedComment>) getListByEqualsField(SQL_GET_JOINED,
                "post_id", postId, joinedCommentRowMapper);
    }

    @Override
    public JoinedComment getJoinedById(Long id) {
        return (JoinedComment) getByEqualsField(SQL_GET_JOINED, "comment.id", id, joinedCommentRowMapper);
    }

    @Override
    void fillGapsInStatement(PreparedStatement preparedStatement, Comment entity) throws SQLException {
        preparedStatement.setLong(1, entity.getUserId());
        preparedStatement.setLong(2, entity.getPostId());
        preparedStatement.setString(3, entity.getText());
        preparedStatement.setDate(4, (Date) entity.getDate());
    }
}
