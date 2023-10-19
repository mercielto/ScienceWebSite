package com.example.webprojectscience.utill.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<E> {
    E from(ResultSet rs, int rowNum) throws SQLException;
}