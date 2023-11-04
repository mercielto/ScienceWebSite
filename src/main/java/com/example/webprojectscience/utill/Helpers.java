package com.example.webprojectscience.utill;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Helpers {
    public static void redirect(HttpServletResponse response, String path) throws IOException {
        response.sendRedirect(path);
    }
}
