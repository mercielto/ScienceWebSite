package com.example.webprojectscience.config;

import com.example.webprojectscience.utill.ImageBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class NavbarMapGetter {
    public static Map<String, Object> getMap(HttpServletRequest request) {
        Map<String, Object> params = new HashMap<>();
        params.put("imageBuilder", new ImageBuilder());
        params.put("contextPath", request.getContextPath());
        return params;
    }
}
