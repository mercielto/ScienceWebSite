package com.example.webprojectscience.service;

import com.example.webprojectscience.models.Comment;
import com.example.webprojectscience.models.Like;
import com.example.webprojectscience.models.Post;
import com.example.webprojectscience.models.joined.JoinedComment;
import com.example.webprojectscience.models.joined.JoinedLike;
import com.example.webprojectscience.models.joined.JoinedPost;
import com.example.webprojectscience.utill.DataBaseManager;

import java.util.ArrayList;
import java.util.List;

public class PostsHandlerService {
    public static List<JoinedPost> getJoinedPosts() {
        List<Post> posts = DataBaseManager.getPostDao().getAll();
        List<JoinedPost> joinedPosts = new ArrayList<>();

        for (Post post : posts) {
            joinedPosts.add(
                    new JoinedPost(
                            post,
                            DataBaseManager.getUserDao().getById(post.getUserId()),
                            DataBaseManager.getThemeDao().getById(post.getThemeId()),
                            getJoinedComments(post.getId()),
                            getJoinedLikes(post.getId())
                    )
            );
        }
        return joinedPosts;
    }

    public static List<JoinedComment> getJoinedComments(Long postId) {
        List<Comment> comments = DataBaseManager.getCommentDao().getByPostId(postId);
        List<JoinedComment> joinedComments = new ArrayList<>();

        for (Comment comment : comments) {
            joinedComments.add(
                    new JoinedComment(
                            comment,
                            DataBaseManager.getUserDao().getById(comment.getId())
                    )
            );
        }
        return joinedComments;
    }
    public static List<JoinedLike> getJoinedLikes(Long postId) {
        List<Like> likes = DataBaseManager.getLikeDao().getLikesByPostId(postId);
        List<JoinedLike> joinedLikes = new ArrayList<>();

        for (Like like : likes) {
            joinedLikes.add(
                    new JoinedLike(
                            like,
                            DataBaseManager.getUserDao().getById(like.getId())
                    )
            );
        }
        return joinedLikes;
    }
}
