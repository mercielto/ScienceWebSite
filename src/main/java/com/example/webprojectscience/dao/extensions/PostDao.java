package com.example.webprojectscience.dao.extensions;

import com.example.webprojectscience.dao.DAO;
import com.example.webprojectscience.models.Post;
import com.example.webprojectscience.models.joined.JoinedPost;
import com.example.webprojectscience.utill.PreparedStatementConditionBuilder;

import java.util.List;

public interface PostDao extends DAO<Post> {
    List<Post> getByUserId(Long userId);
    List<Post> getByThemeId(Long themeId);
    List<Post> getByTags(String[] tags);
    Post getByLink(String link);
    List<JoinedPost> getJoined();
    JoinedPost getJoinedByLink(String link);
    List<JoinedPost> getJoinedByUserId(Long userId);
    List<JoinedPost> getJoinedByAuthorId(PreparedStatementConditionBuilder builder, List<Long> userIdList);
    List<JoinedPost> getJoined(PreparedStatementConditionBuilder builder, List<Object> values);

    List<JoinedPost> getJoined(PreparedStatementConditionBuilder builder);
}
