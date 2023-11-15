package com.example.webprojectscience.dao.impl;

import com.example.webprojectscience.dao.extensions.QuestionDao;
import com.example.webprojectscience.models.Question;
import com.example.webprojectscience.models.joined.JoinedQuestion;
import com.example.webprojectscience.utill.PreparedStatementConditionBuilder;
import com.example.webprojectscience.utill.RowMapper.RowMapper;

import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoImpl extends AbstractDAOImpl<Question> implements QuestionDao {
    public final String SQL_GET_JOINED = "SELECT fq.*, us.*, theme.*, fq.id \"question_id_\"," +
            " fq.user_id \"user_id_\", fq.date \"question_date_\", fq.text \"question_text_\"," +
            " fq.link \"question_link_\", us.link \"user_link_\", us.name \"user_name_\"," +
            " theme.name \"theme_name_\" FROM forum_question as fq, \"User\" as us, theme " +
            "WHERE fq.user_id = us.id AND theme.id = fq.theme_id";
    private RowMapper<JoinedQuestion> joinedQuestionRowMapper;

    public QuestionDaoImpl(Connection connection, String tableName, RowMapper<Question> rowMapper,
                           RowMapper<JoinedQuestion> joinedQuestionRowMapper1) {
        super(connection, tableName, rowMapper);
        joinedQuestionRowMapper = joinedQuestionRowMapper1;

        SQL_INSERT = "INSERT INTO forum_question (user_id, date, text, tags, theme_id," +
                " link, main_question, answered_answer_id)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        SQL_UPDATE = "UPDATE forum_question SET user_id = ?, date = ?, text = ?," +
                " tags = ?, theme_id = ?, link = ?, main_question = ?, answered_answer_id = ? WHERE id = ?";
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
        preparedStatement.setObject(8, entity.getAnswerId());
    }

    @Override
    public List<Question> getByDate(java.util.Date from, java.util.Date to) {
        PreparedStatementConditionBuilder builder = new PreparedStatementConditionBuilder(SQL_GET);
        List<Object> values = new ArrayList<>();
        if (from == null) {
            from = Date.valueOf(LocalDate.of(1000, Month.APRIL, 3));
        }

        builder.lessEquals("date");
        values.add(from);

        if (to == null) {
            to = Date.valueOf(LocalDate.now());
        }

        builder.moreEquals("date");
        values.add(to);

        PreparedStatement preparedStatement = getPreparedStatement(builder.get(), values);
        return (List<Question>) executeSqlPreparedStatement(preparedStatement, rowMapper);
    }

    @Override
    public List<Question> getByThemeId(Long id) {
        return getEntitiesByEqualsField("theme_id", id);
    }

    @Override
    public List<Question> getAllAnswered() {
        PreparedStatementConditionBuilder builder = new PreparedStatementConditionBuilder(SQL_GET);
        builder.isNotNull("answered_answer_id");
        PreparedStatement preparedStatement = getPreparedStatement(builder.get(), List.of());
        return (List<Question>) executeSqlPreparedStatement(preparedStatement, rowMapper);
    }

    @Override
    public Question getByLink(String link) {
        return getByEqualsField("link", link);
    }

    private JoinedQuestion getJoinedQuestionByField(String fieldName, Object value) {
        return (JoinedQuestion) getByEqualsField(SQL_GET_JOINED, fieldName, value, joinedQuestionRowMapper);
    }

    @Override
    public JoinedQuestion getJoinedQuestionByLink(String link) {
        return getJoinedQuestionByField("fq.link", link);
    }

    @Override
    public List<JoinedQuestion> getJoinedQuestionsByUserId(Long userId) {
        return (List<JoinedQuestion>) getListByEqualsField(SQL_GET_JOINED, "user_id",
                userId, joinedQuestionRowMapper);
    }

    public List<JoinedQuestion> getJoinedQuestions(PreparedStatementConditionBuilder builder, List<Object> values) {
        builder.setSql(SQL_GET_JOINED);
        PreparedStatement preparedStatement = getPreparedStatement(builder.get(), values);
        return (List<JoinedQuestion>) executeSqlPreparedStatement(preparedStatement, joinedQuestionRowMapper);
    }

    private List<JoinedQuestion> getJoinedQuestionListByField(String fieldName, Object value) {
        return (List<JoinedQuestion>) getListByEqualsField(SQL_GET_JOINED, fieldName, value, joinedQuestionRowMapper);
    }

}
