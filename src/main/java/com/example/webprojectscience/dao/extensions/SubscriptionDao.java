package com.example.webprojectscience.dao.extensions;

import com.example.webprojectscience.dao.DAO;
import com.example.webprojectscience.models.Subscription;
import com.example.webprojectscience.models.joined.JoinedSubscription;

import java.util.List;

public interface SubscriptionDao extends DAO<Subscription> {
    List<Subscription> getSubscriptions(Long userId);
    List<Subscription> getSubscribers(Long subscriberId);
    boolean isSubscribed(Long userId, Long subscribedToId);
    Subscription getSubscription(Long userId, Long subscribedToId);
    List<JoinedSubscription> getJoinedSubscribers(Long userId);
    List<JoinedSubscription> getJoinedSubscriptions(Long userId);
}
