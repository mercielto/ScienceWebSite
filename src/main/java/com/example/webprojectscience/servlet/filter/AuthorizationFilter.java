package com.example.webprojectscience.servlet.filter;

import com.example.webprojectscience.models.User;
import com.example.webprojectscience.service.AuthorizationService;
import com.example.webprojectscience.utill.Helpers;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebFilter(servletNames = {"LoginServlet", "RegistrationServlet"})
public class AuthorizationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Optional<User> user = Optional.ofNullable(AuthorizationService.getAuthorizedUser(request));

        if (user.isPresent()) {
            Helpers.redirect(response, request.getContextPath());
            return;
        }

        filterChain.doFilter(request, response);
    }
}

