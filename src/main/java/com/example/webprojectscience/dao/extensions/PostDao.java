package com.example.webprojectscience.dao.extensions;

import com.example.webprojectscience.dao.DAO;
import com.example.webprojectscience.models.Post;

import java.util.List;

public interface PostDao extends DAO<Post> {
    List<Post> getByUserId(Long userId);
    List<Post> getByThemeId(Long themeId);
    List<Post> getByTags(String[] tags);
    Post getByLink(String link);
}
