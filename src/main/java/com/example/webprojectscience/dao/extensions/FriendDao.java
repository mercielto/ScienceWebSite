package com.example.webprojectscience.dao.extensions;

import com.example.webprojectscience.dao.DAO;
import com.example.webprojectscience.models.Friend;

import java.util.List;

public interface FriendDao extends DAO<Friend> {
    List<Friend> getFriends(Long userId);
}
