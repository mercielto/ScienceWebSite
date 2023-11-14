package com.example.webprojectscience.dao.extensions;

import com.example.webprojectscience.dao.DAO;
import com.example.webprojectscience.models.Comment;
import com.example.webprojectscience.models.joined.JoinedComment;

import java.util.List;

public interface CommentDao extends DAO<Comment> {
    List<Comment> getByUserId(Long userId);
    List<Comment> getByPostId(Long postId);

    List<JoinedComment> getJoinedByPostId(long postId);
}
