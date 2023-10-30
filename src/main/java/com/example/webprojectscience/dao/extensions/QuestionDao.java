package com.example.webprojectscience.dao.extensions;

import com.example.webprojectscience.dao.DAO;
import com.example.webprojectscience.models.Question;

import java.util.Date;
import java.util.List;

public interface QuestionDao extends DAO<Question> {
    List<Question> getByDate(Date from, Date to);
    List<Question> getByThemeId(Long id);
}
