package com.example.webprojectscience.servlet.filter;

import com.example.webprojectscience.models.User;
import com.example.webprojectscience.service.AuthorizationService;
import com.example.webprojectscience.utill.Helpers;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(servletNames = {"VoteServlet", "WhoVotedServlet"})
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        User user = AuthorizationService.getUserBySessionToken(request);
        if (user == null) {
            user = AuthorizationService.getUserByCookieToken(request);

            if (user == null) {
                Helpers.redirect(response, request.getContextPath() + "/login");
                return;
            }
        }

        filterChain.doFilter(request        , response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

