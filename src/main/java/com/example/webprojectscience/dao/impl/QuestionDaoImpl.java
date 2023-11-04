package com.example.webprojectscience.dao.impl;

import com.example.webprojectscience.dao.extensions.QuestionDao;
import com.example.webprojectscience.models.Question;
import com.example.webprojectscience.utill.RowMapper.RowMapper;

import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoImpl extends AbstractDAOImpl<Question> implements QuestionDao {
    protected final String SQL_GET_BY_DATE = "SELECT * FROM forum_question WHERE date <= ? AND date >= ?";

    public QuestionDaoImpl(Connection connection, String tableName, RowMapper<Question> rowMapper) {
        super(connection, tableName, rowMapper);

        SQL_INSERT = "INSERT INTO forum_question (user_id, date, text, tags, theme_id, link, main_question)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?)";
        SQL_UPDATE = "UPDATE forum_question SET user_id = ?, date = ?, text = ?," +
                " tags = ?, theme_id = ?, link = ?, main_question = ? WHERE id = ?";
    }

    @Override
    void fillGapsInStatement(PreparedStatement preparedStatement, Question entity) throws SQLException {
        preparedStatement.setLong(1, entity.getUserId());
        preparedStatement.setDate(2, (Date) entity.getDate());
        preparedStatement.setString(3, entity.getText());
        preparedStatement.setArray(4, connection.createArrayOf("VARCHAR", entity.getTags().toArray()));
        preparedStatement.setLong(5, entity.getThemeId());
        preparedStatement.setString(6, entity.getLink());
        preparedStatement.setString(7, entity.getMainQuestion());
    }

    @Override
    public List<Question> getByDate(java.util.Date from, java.util.Date to) {
        if (from == null) {
            from = Date.valueOf(LocalDate.of(1000, Month.APRIL, 3));
        }

        if (to == null) {
            to = Date.valueOf(LocalDate.now());
        }

        List<Question> questions = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BY_DATE);
            preparedStatement.setDate(1, (Date) to);
            preparedStatement.setDate(2, (Date) from);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                questions.add(rowMapper.from(rs));
            }
            return questions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Question> getByThemeId(Long id) {
        return getEntitiesByField("theme_id", id);
    }

    @Override
    public Question getByLink(String link) {
        return getByField("link", link);
    }

}
