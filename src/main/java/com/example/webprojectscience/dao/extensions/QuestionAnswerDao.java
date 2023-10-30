package com.example.webprojectscience.dao.extensions;

import com.example.webprojectscience.dao.DAO;
import com.example.webprojectscience.models.QuestionAnswer;

import java.util.List;

public interface QuestionAnswerDao extends DAO<QuestionAnswer> {
    List<QuestionAnswer> getByQuestionId(Long id);
}
