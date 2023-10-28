package com.example.webprojectscience.service;

import com.example.webprojectscience.models.Theme;
import com.example.webprojectscience.utill.DataBaseManager;

import java.util.List;

public class ThemesHandlerService {
    public static List<Theme> getAllThemes() {
        List<Theme> themes = DataBaseManager.getThemeDao().getAll();

        // "Other" должен выводиться под конец и он постоянен
        for (Theme theme : themes) {
            if (theme.getName().equals("Other")) {
                themes.remove(theme);
                break;
            }
        }

        return themes;
    }

    public static Theme getOther() {
        return DataBaseManager.getThemeDao().getByName("Other");
    }
}
