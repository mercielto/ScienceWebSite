package com.example.webprojectscience.servlet.filter;

import com.example.webprojectscience.models.User;
import com.example.webprojectscience.service.AuthorizationService;
import com.example.webprojectscience.utill.DataBaseManager;
import com.example.webprojectscience.utill.Helpers;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(servletNames = {"ProfileSettingsServlet"})
public class PageAccessFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        User user = AuthorizationService.getAuthorizedUser(request);
        if (user == null) {
            Helpers.redirect(response, request.getContextPath());
        }

        filterChain.doFilter(request, response);
    }
}
