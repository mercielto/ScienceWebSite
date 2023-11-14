package com.example.webprojectscience.dao.impl;

import com.example.webprojectscience.dao.extensions.LikeDao;
import com.example.webprojectscience.models.Like;
import com.example.webprojectscience.models.joined.JoinedLike;
import com.example.webprojectscience.utill.RowMapper.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class LikeDaoImpl extends AbstractDAOImpl<Like> implements LikeDao {
    public final String SQL_GET_JOINED = "SELECT *, lk.id \"like_id_\", us.link \"user_link_\", post.link \"post_link_\" " +
            "FROM \"Like\" as lk, \"User\" as us, post WHERE lk.user_id = us.id AND lk.post_id = post.id";

    private RowMapper<JoinedLike> joinedLikeRowMapper;
    public LikeDaoImpl(Connection connection, String tableName, RowMapper<Like> rowMapper, RowMapper<JoinedLike> joinedLikeRowMapper1) {
        super(connection, tableName, rowMapper);
        joinedLikeRowMapper = joinedLikeRowMapper1;

        SQL_INSERT = "INSERT INTO \"Like\" (user_id, post_id) values (?, ?)";
        SQL_UPDATE = "UPDATE \"Like\" set user_id = ?, post_id = ? where id = ?";
    }

    @Override
    void fillGapsInStatement(PreparedStatement preparedStatement, Like entity) throws SQLException {
        preparedStatement.setLong(1, entity.getUserId());
        preparedStatement.setLong(2, entity.getPostId());
    }

    @Override
    public List<Like> getLikesByUserId(Long id) {
        return getEntitiesByEqualsField("user_id", id);
    }

    @Override
    public List<Like> getLikesByPostId(Long postId) {
        return getEntitiesByEqualsField("post_id", postId);
    }

    @Override
    public List<JoinedLike> getJoinedByPostId(long postId) {
        return (List<JoinedLike>) getListByEqualsField(SQL_GET_JOINED, "post_id", postId, joinedLikeRowMapper);
    }
}
