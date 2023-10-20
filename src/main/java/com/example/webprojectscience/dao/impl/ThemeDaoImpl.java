package com.example.webprojectscience.dao.impl;

import com.example.webprojectscience.dao.extensions.ThemeDao;
import com.example.webprojectscience.models.Theme;
import com.example.webprojectscience.utill.RowMapper.RowMapper;
import com.example.webprojectscience.utill.RowMapper.impl.ThemeRowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ThemeDaoImpl extends AbstractDAOImpl<Theme> implements ThemeDao {
    public ThemeDaoImpl(Connection connection, String tableName, RowMapper<Theme> rowMapper) {
        super(connection, tableName, rowMapper);

        SQL_INSERT = "INSERT INTO \"theme\" (name, picture_path) VALUES (?, ?)";
        SQL_UPDATE = "UPDATE \"theme\" SET name = ?, picture_path = ? WHERE id = ?";
    }

    @Override
    protected void fillGapsInStatement(PreparedStatement preparedStatement, Theme entity) throws SQLException {
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setString(2, entity.getPicturePath());
        preparedStatement.setLong(3, entity.getId());
    }

    @Override
    public Theme getByName(String name) {
        return getByField("name", name);
    }
}
