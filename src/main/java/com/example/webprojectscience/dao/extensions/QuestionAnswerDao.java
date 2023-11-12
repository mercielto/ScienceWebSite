package com.example.webprojectscience.dao.extensions;

import com.example.webprojectscience.dao.DAO;
import com.example.webprojectscience.models.QuestionAnswer;
import com.example.webprojectscience.models.joined.JoinedAnswer;
import com.example.webprojectscience.models.joined.JoinedQuestion;

import java.util.List;

public interface QuestionAnswerDao extends DAO<QuestionAnswer> {
    List<QuestionAnswer> getByQuestionId(Long id);
    List<JoinedAnswer> getJoinedAnswersByQuestionId(Long questionId);
}
