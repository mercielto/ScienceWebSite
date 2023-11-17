package com.example.webprojectscience.dao.impl;

import com.example.webprojectscience.dao.extensions.QuestionAnswerDao;
import com.example.webprojectscience.models.QuestionAnswer;
import com.example.webprojectscience.models.joined.JoinedAnswer;
import com.example.webprojectscience.utill.RowMapper.RowMapper;

import java.sql.*;
import java.util.List;

public class QuestionAnswerDaoImpl extends AbstractDAOImpl<QuestionAnswer> implements QuestionAnswerDao{
    public static String SQL_GET_JOINED = "SELECT *, fa.id \"answer_id\" from forum_answer as fa, \"User\" as us" +
            " WHERE fa.user_id = us.id";
    private RowMapper<JoinedAnswer> joinedAnswerRowMapper;
    public QuestionAnswerDaoImpl(Connection connection, String tableName, RowMapper<QuestionAnswer> rowMapper,
                                 RowMapper<JoinedAnswer> joinedAnswerRowMapper1) {
        super(connection, tableName, rowMapper);
        SQL_INSERT = "INSERT INTO forum_answer (user_id, question_id, text, date) VALUES (?, ?, ?, ?)";
        SQL_UPDATE = "UPDATE forum_question SET user_id = ?, question_id = ?, text = ?, date = ? WHERE id = ?";
        joinedAnswerRowMapper = joinedAnswerRowMapper1;
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
        return getEntitiesByEqualsField("question_id", id);
    }

    @Override
    public List<JoinedAnswer> getJoinedAnswersByQuestionId(Long questionId) {
        return (List<JoinedAnswer>) getListByEqualsField(SQL_GET_JOINED, "question_id", questionId, joinedAnswerRowMapper);
    }

}
