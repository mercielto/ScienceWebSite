package com.example.webprojectscience.utill;

import com.example.webprojectscience.models.Token;
import com.example.webprojectscience.models.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Helpers {
    public static void redirect(HttpServletResponse response, String path) throws IOException {
        response.sendRedirect(path);
    }

}
