package com.example.webprojectscience.config;

import com.example.webprojectscience.models.User;
import com.example.webprojectscience.utill.FileBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class NavbarMapGetter {
    public static Map<String, Object> getMap(HttpServletRequest request, User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("fileBuilder", new FileBuilder());
        params.put("contextPath", request.getContextPath());
        params.put("option", Optional.ofNullable(user));
        return params;
    }
}
