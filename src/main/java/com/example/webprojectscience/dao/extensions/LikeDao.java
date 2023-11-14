package com.example.webprojectscience.dao.extensions;

import com.example.webprojectscience.dao.DAO;
import com.example.webprojectscience.models.Like;
import com.example.webprojectscience.models.joined.JoinedLike;

import java.util.List;

public interface LikeDao extends DAO<Like> {
    List<Like> getLikesByUserId(Long id);
    List<Like> getLikesByPostId(Long postId);

    List<JoinedLike> getJoinedByPostId(long postId);
}
