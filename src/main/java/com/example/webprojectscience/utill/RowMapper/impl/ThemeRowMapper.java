package com.example.webprojectscience.utill.RowMapper.impl;

import com.example.webprojectscience.models.Theme;
import com.example.webprojectscience.utill.RowMapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ThemeRowMapper implements RowMapper<Theme> {
    @Override
    public Theme from(ResultSet rs, int rowNum) throws SQLException {
        return new Theme(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("picture_path")
        );
    }
}
