package com.example.webprojectscience.dao.impl;

import com.example.webprojectscience.dao.extensions.PostDao;
import com.example.webprojectscience.models.Post;
import com.example.webprojectscience.utill.PreparedStatementConditionBuilder;
import com.example.webprojectscience.utill.RowMapper.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PostDaoImpl extends AbstractDAOImpl<Post> implements PostDao {
    public String SQL_GET_BY_TAGS = "SELECT * FROM \"post\" WHERE tags @> ?";

    public PostDaoImpl(Connection connection, String tableName, RowMapper<Post> rowMapper) {
        super(connection, tableName, rowMapper);

        SQL_INSERT = "INSERT INTO post (user_id, path_in_storage, theme_id, tags, date, title, link) values (?, ?, ?, ?, ?, ?, ?)";
        SQL_UPDATE = "UPDATE post SET user_id = ?, path_in_storage = ?, theme_id = ?," +
                " tags = ?, date = ?, title = ?, link = ? WHERE id = ?";
    }


    @Override
    public List<Post> getByUserId(Long userId) {
        return getEntitiesByField("user_id", userId);
    }

    @Override
    public List<Post> getByThemeId(Long themeId) {
        return getEntitiesByField("theme_id", themeId);
    }

    @Override
    public List<Post> getByTags(String[] tags) {
        PreparedStatementConditionBuilder builder = new PreparedStatementConditionBuilder(SQL_GET);
        builder.contains("tags");
        PreparedStatement preparedStatement = getPreparedStatement(SQL_GET, List.of(Arrays.toString(tags)));
        return (List<Post>)(Object)executeSqlPreparedStatement(preparedStatement, rowMapper);
    }

    @Override
    public Post getByLink(String link) {
        return getByField("link", link);
    }

    @Override
    void fillGapsInStatement(PreparedStatement preparedStatement, Post entity) throws SQLException {
        preparedStatement.setLong(1, entity.getUserId());
        preparedStatement.setString(2, entity.getPathInStorage());
        preparedStatement.setLong(3, entity.getThemeId());
        preparedStatement.setArray(4, (Array) entity.getTags());
        preparedStatement.setString(5, entity.getTitle());
        preparedStatement.setString(6, entity.getLink());
    }
}
