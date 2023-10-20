package com.example.webprojectscience.service;

import com.example.webprojectscience.models.Token;
import com.example.webprojectscience.models.User;
import com.example.webprojectscience.utill.DataBaseManager;
import com.example.webprojectscience.utill.Encryption;
import com.example.webprojectscience.utill.Generator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AuthorizationService {
    public static boolean authorizeByCookieToken(HttpServletRequest request) {
        User user = getUserByCookieToken(request);

        if (user == null) {
            return false;
        }
        setSessionAttributeToken(request, user);
        return true;
    }

    public static User getUserByCookieToken(HttpServletRequest request) {
        Token token = getTokenFromCookies(request);
        if (token == null) {
            return null;
        }
        return DataBaseManager.getUserDao().getById(token.getUserId());
    }

    public static void setSessionAttributeToken(HttpServletRequest request, User user) {
        Token token = generateTokenEntity(request, user);
        DataBaseManager.getTokenDao().insert(token);

        request.getSession().setAttribute("authorized", token.getValue());
    }

    public static Token generateTokenEntity(HttpServletRequest request, User user) {
        Token token = new Token();
        token.setValue(String.valueOf(Generator.generateToken()));
        token.setIpAddress(request.getRemoteAddr());
        token.setUserId(user.getId());
        return token;
    }

    public static Token getTokenFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("authorized")) {
                Token token = DataBaseManager.getTokenDao().getByValue(cookie.getValue());
                if (token != null) {
                    return token;
                }
                return null;
            }
        }
        return null;
    }

    public static User getAuthorizedUser(HttpServletRequest request) {
        User user = getUserBySessionToken(request);
        if (user != null) {
            return user;
        }
        return getUserByCookieToken(request);
    }

    public static boolean login(HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = Encryption.encrypt(request.getParameter("password"));

        User user = DataBaseManager.getUserDao().getByLogin(login);
        if (user == null) {
            return false;
        }

        if (user.getPassword().equals(password)) {
            setSessionAttributeToken(request, user);
            return true;
        }
        return false;
    }

    public static User getUserBySessionToken(HttpServletRequest request) {
        Token token = getTokenFromSession(request);
        if (token == null) {
            return null;
        }

        if (token.getIpAddress().equals(request.getRemoteAddr())) {
            return DataBaseManager.getUserDao().getById(token.getUserId());
        }
        return null;
    }

    public static Token getTokenFromSession(HttpServletRequest request) {
        String value = (String) request.getSession().getAttribute("authorized");
        if (value == null) {
            return null;
        }
        return DataBaseManager.getTokenDao().getByValue(value);
    }

    public static void saveInCookies(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");

        User user = DataBaseManager.getUserDao().getByLogin(login);
        Token token = generateTokenEntity(request, user);

        DataBaseManager.getTokenDao().insert(token);
        Cookie tok = new Cookie("authorized", String.valueOf(token.getValue()));

        response.addCookie(tok);
    }

//    public static boolean checkCookies(HttpServletRequest request) {
//        Cookie[] cookies = request.getCookies();
//
//        if (cookies == null) {
//            return false;
//        }
//
//        for (Cookie cookie : cookies) {
//            if (cookie.getName().equals("authorized")) {
//                Token token = DataBaseManager.getTokenDao().getByValue(cookie.getValue());
//
//                if (token != null) {
//                    return true;
//                }
//                return false;
//            }
//        }
//        return false;
//    }

    public static void deleteCookies(HttpServletRequest request, HttpServletResponse response, List<String> names) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return;
        }
        for (Cookie cookie : cookies) {
            if (names.contains(cookie.getName())) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }
}
