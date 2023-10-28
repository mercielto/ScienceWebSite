package com.example.webprojectscience.dao.extensions;

import com.example.webprojectscience.dao.DAO;
import com.example.webprojectscience.models.Like;

import java.util.List;

public interface LikeDao extends DAO<Like> {
    List<Like> getLikesById(Long id);
}
