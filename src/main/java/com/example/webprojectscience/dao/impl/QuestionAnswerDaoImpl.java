package com.example.webprojectscience.dao.impl;

import com.example.webprojectscience.dao.extensions.QuestionAnswerDao;
import com.example.webprojectscience.models.QuestionAnswer;
import com.example.webprojectscience.utill.RowMapper.RowMapper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class QuestionAnswerDaoImpl extends AbstractDAOImpl<QuestionAnswer> implements QuestionAnswerDao{
    public QuestionAnswerDaoImpl(Connection connection, String tableName, RowMapper<QuestionAnswer> rowMapper) {
        super(connection, tableName, rowMapper);

        SQL_INSERT = "INSERT INTO forum_answer (user_id, question_id, text, date) VALUES (?, ?, ?, ?)";
            SQL_UPDATE = "UPDATE forum_question SET user_id = ?, question_id = ?, text = ?, date = ? WHERE id = ?";
    }

    @Override
    void fillGapsInStatement(PreparedStatement preparedStatement, QuestionAnswer entity) throws SQLException {
        preparedStatement.setLong(1, entity.getUserId());
        preparedStatement.setLong(2, entity.getQuestionId());
        preparedStatement.setString(3, entity.getText());
        preparedStatement.setDate(4, (Date) entity.getDate());
    }

    @Override
    public List<QuestionAnswer> getByQuestionId(Long id) {
        return getEntitiesByField("question_id", id);
    }
}
