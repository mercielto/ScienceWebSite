package com.example.webprojectscience.dao.extensions;

import com.example.webprojectscience.dao.DAO;
import com.example.webprojectscience.models.Comment;

import java.util.List;

public interface CommentDao extends DAO<Comment> {
    List<Comment> getByUserId(Long userId);
    List<Comment> getByPostId(Long postId);
}
