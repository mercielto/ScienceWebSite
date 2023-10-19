package com.example.webprojectscience.servlet.listener;



import com.example.webprojectscience.config.FreemarkerConfigSingleton;
import com.example.webprojectscience.dao.impl.TokenDAOImpl;
import com.example.webprojectscience.dao.impl.UserDAOImpl;
import com.example.webprojectscience.utill.ConnectionSingleton;
import com.example.webprojectscience.utill.DataBaseManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServerListenerServlet implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
        FreemarkerConfigSingleton.setServletContext(ctx);

        DataBaseManager.setUserDao(new UserDAOImpl(ConnectionSingleton.getConnection()));
        DataBaseManager.setTokenDao(new TokenDAOImpl(ConnectionSingleton.getConnection()));
    }
}
