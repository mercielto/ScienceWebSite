package com.example.webprojectscience.utill;

import com.example.webprojectscience.models.Theme;
import com.example.webprojectscience.models.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class FileBuilder {
    public String getProfilePhotoInBytes(User user) {
        return ImageProcessor.encodeImage(FileStoragePathBuilder.getPathToProfileImage(user.getProfilePhotoPath()));
    }

    public String getThemePhotoInBytes(Theme theme) {
        return ImageProcessor.encodeImage(FileStoragePathBuilder.getPathThemePhoto(theme.getPicturePath()));
    }

    public String getServicePhotoInBytes(String name) {
        return ImageProcessor.encodeImage(FileStoragePathBuilder.getPathToServiceImg(name));
    }

    public String getPostText(String link) {
        String path = FileStoragePathBuilder.getPathToPostTxt(link);
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return String.join("<br>", bufferedReader.lines().toList());
    }
}
