package com.example.webprojectscience.dao.extensions;

import com.example.webprojectscience.dao.DAO;
import com.example.webprojectscience.models.Theme;

public interface ThemeDao extends DAO<Theme> {
    Theme getByName(String name);
}
