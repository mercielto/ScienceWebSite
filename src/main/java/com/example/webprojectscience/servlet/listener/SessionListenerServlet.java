package com.example.webprojectscience.servlet.listener;


import com.example.webprojectscience.utill.DataBaseManager;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListenerServlet implements HttpSessionListener {
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSessionListener.super.sessionDestroyed(se);

        String tokenValue = (String) se.getSession().getAttribute("authorized");

        if (tokenValue != null) {
            DataBaseManager.getTokenDao().deleteByValue(tokenValue);
        }
    }
}
