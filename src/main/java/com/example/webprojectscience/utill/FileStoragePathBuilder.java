package com.example.webprojectscience.utill;

import com.example.webprojectscience.config.Params;

public class FileStoragePathBuilder {
    public static String getPathToProfileImage(String imagePath) {
        return Params.storagePath + "profile\\" + imagePath;
    }

    public static String getPathThemePhoto(String path) {
        return Params.storagePath + "theme_images\\" + path;
    }

    public static String getPathToServiceImg(String path) {
        return Params.storagePath + "service_files\\img\\" + path;
    }

    public static String getPathToPostTxt(String path) {
        return Params.storagePath + "service_files\\txt\\" + path;
    }
}
