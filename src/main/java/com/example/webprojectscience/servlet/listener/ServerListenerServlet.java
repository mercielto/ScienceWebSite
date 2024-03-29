package com.example.webprojectscience.servlet.listener;

import com.example.webprojectscience.config.FreemarkerConfigSingleton;
import com.example.webprojectscience.config.Params;
import com.example.webprojectscience.dao.impl.*;
import com.example.webprojectscience.utill.ConnectionSingleton;
import com.example.webprojectscience.utill.DataBaseManager;
import com.example.webprojectscience.utill.RowMapper.impl.*;
import com.example.webprojectscience.utill.RowMapper.impl.joined.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServerListenerServlet implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
        ctx.setAttribute("limit", 2);

        FreemarkerConfigSingleton.setServletContext(ctx);

        DataBaseManager.setUserDao(new UserDAOImpl(ConnectionSingleton.getConnection(),
                Params.userTableName, new UserRowMapper()));
        DataBaseManager.setTokenDao(new TokenDAOImpl(ConnectionSingleton.getConnection(),
                Params.tokenTableName, new TokenRowMapper()));
        DataBaseManager.setCommentDao(new CommentDaoImpl(ConnectionSingleton.getConnection(),
                Params.commentTableName, new CommentRowMapper(), new JoinedCommentRowMapper()));
        DataBaseManager.setFriendDao(new FriendDaoImpl(ConnectionSingleton.getConnection(),
                Params.friendTableName, new FriendRowMapper()));
        DataBaseManager.setLikeDao(new LikeDaoImpl(ConnectionSingleton.getConnection(),
                Params.likeTableName, new LikeRowMapper(), new JoinedLikeRowMapper()));
        DataBaseManager.setPostDao(new PostDaoImpl(ConnectionSingleton.getConnection(),
                Params.postTableName, new PostRowMapper(), new JoinedPostRowMapper()));
        DataBaseManager.setThemeDao(new ThemeDaoImpl(ConnectionSingleton.getConnection(),
                Params.themeTableName, new ThemeRowMapper()));
        DataBaseManager.setSubscriptionDao(new SubscriptionDaoImpl(ConnectionSingleton.getConnection(),
                Params.subscriptionTableName, new SubscriptionRowMapper(), new JoinedSubscriptionRowMapper()));
        DataBaseManager.setQuestionDao(new QuestionDaoImpl(ConnectionSingleton.getConnection(),
                Params.forumQuestionTableName, new QuestionRowMapper(), new JoinedQuestionRowMapper()));
        DataBaseManager.setQuestionAnswerDao(new QuestionAnswerDaoImpl(ConnectionSingleton.getConnection(),
                Params.forumQuestionAnswerTableName, new QuestionAnswerRowMapper(), new JoinedAnswerRowMapper()));
//        DataBaseManager.setMessage()
    }
}
