package com.example.webprojectscience.dao.extensions;

import com.example.webprojectscience.dao.DAO;
import com.example.webprojectscience.models.Question;
import com.example.webprojectscience.models.joined.JoinedQuestion;
import com.example.webprojectscience.utill.PreparedStatementConditionBuilder;

import java.util.Date;
import java.util.List;

public interface QuestionDao extends DAO<Question> {
    List<Question> getByDate(Date from, Date to);
    List<Question> getByThemeId(Long id);
    List<Question> getAllAnswered();
    Question getByLink(String link);

    JoinedQuestion getJoinedQuestionByLink(String link);
    List<JoinedQuestion> getJoinedQuestionsByUserId(Long userId);
    List<JoinedQuestion> getJoinedQuestions(PreparedStatementConditionBuilder builder, List<Object> values);
}
