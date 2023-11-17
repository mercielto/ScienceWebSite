package com.example.webprojectscience.dao.impl;

import com.example.webprojectscience.dao.extensions.PostDao;
import com.example.webprojectscience.models.Post;
import com.example.webprojectscience.models.joined.JoinedPost;
import com.example.webprojectscience.utill.PreparedStatementConditionBuilder;
import com.example.webprojectscience.utill.RowMapper.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PostDaoImpl extends AbstractDAOImpl<Post> implements PostDao {
    public final String SQL_GET_JOINED = "SELECT *, post.id \"post_id\", post.link \"post_link_\", us.name \"user_name_\"," +
            " us.link \"user_link_\", theme.name \"theme_name_\" FROM post, \"User\" as us, theme " +
            "WHERE post.user_id = us.id AND post.theme_id = theme.id";
    private RowMapper<JoinedPost> joinedPostRowMapper;

    public PostDaoImpl(Connection connection, String tableName, RowMapper<Post> rowMapper, RowMapper<JoinedPost> joinedPostRowMapper1) {
        super(connection, tableName, rowMapper);
        joinedPostRowMapper = joinedPostRowMapper1;

        SQL_INSERT = "INSERT INTO post (user_id, path_in_storage, theme_id, tags, date, title, link) values (?, ?, ?, ?, ?, ?, ?)";
        SQL_UPDATE = "UPDATE post SET user_id = ?, path_in_storage = ?, theme_id = ?," +
                " tags = ?, date = ?, title = ?, link = ? WHERE id = ?";
    }


    @Override
    public List<Post> getByUserId(Long userId) {
        return getEntitiesByEqualsField("user_id", userId);
    }

    @Override
    public List<Post> getByThemeId(Long themeId) {
        return getEntitiesByEqualsField("theme_id", themeId);
    }

    @Override
    public List<Post> getByTags(String[] tags) {
        return (List<Post>) getListByEqualsField(SQL_GET, "tags", Arrays.toString(tags), rowMapper);
    }

    @Override
    public Post getByLink(String link) {
        return getByEqualsField("link", link);
    }

    @Override
    public List<JoinedPost> getJoined() {
        return (List<JoinedPost>) executeSqlStatement(SQL_GET_JOINED, joinedPostRowMapper);
    }

    @Override
    public JoinedPost getJoinedByLink(String link) {
        return (JoinedPost) getByEqualsField(SQL_GET_JOINED, "post.link", link, joinedPostRowMapper);
    }

    @Override
    public List<JoinedPost> getJoinedByUserId(Long userId) {
        return (List<JoinedPost>) getListByEqualsField(SQL_GET_JOINED, "user_id", userId, joinedPostRowMapper);
    }

    @Override
    public List<JoinedPost> getJoinedByAuthorId(PreparedStatementConditionBuilder builder, List<Long> userIdList) {
        builder.setSql(SQL_GET_JOINED);
        builder.contains("user_id", userIdList.size());
        PreparedStatement preparedStatement = getPreparedStatement(builder.get(), (List<Object>) (Object) userIdList);
        return (List<JoinedPost>) executeSqlPreparedStatement(preparedStatement, joinedPostRowMapper);
    }

    @Override
    public List<JoinedPost> getJoined(PreparedStatementConditionBuilder builder, List<Object> values) {
        builder.setSql(SQL_GET_JOINED);
        builder.orderBy("date DESC");
        PreparedStatement preparedStatement = getPreparedStatement(builder.get(), values);
        return (List<JoinedPost>) executeSqlPreparedStatement(preparedStatement, joinedPostRowMapper);
    }

    @Override
    public List<JoinedPost> getJoined(PreparedStatementConditionBuilder builder) {
        builder.setSql(SQL_GET_JOINED);
        PreparedStatement preparedStatement = getPreparedStatement(builder.get(), List.of());
        return (List<JoinedPost>) executeSqlPreparedStatement(preparedStatement, joinedPostRowMapper);
    }

    @Override
    void fillGapsInStatement(PreparedStatement preparedStatement, Post entity) throws SQLException {
        preparedStatement.setLong(1, entity.getUserId());
        preparedStatement.setString(2, entity.getPathInStorage());
        preparedStatement.setLong(3, entity.getThemeId());
        preparedStatement.setArray(4, connection.createArrayOf("varchar",
                entity.getTags().toArray()));
        preparedStatement.setDate(5, (Date) entity.getDate());
        preparedStatement.setString(6, entity.getTitle());
        preparedStatement.setString(7, entity.getLink());
    }
}
