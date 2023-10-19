package com.example.webprojectscience.config;


import freemarker.template.Configuration;

import javax.servlet.ServletContext;

public class FreemarkerConfigSingleton {
    private static Configuration cfg;
    public static ServletContext sc;

    public static void setServletContext(ServletContext serv) {
        sc = serv;
    }
    public static Configuration getConfig() {
        if (cfg == null) {
            cfg = new Configuration(Configuration.VERSION_2_3_32);
            cfg.setServletContextForTemplateLoading(sc, "/WEB-INF/templates");
        }
        return cfg;
    }
}
