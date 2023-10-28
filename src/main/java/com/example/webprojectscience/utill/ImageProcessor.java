package com.example.webprojectscience.utill;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

public class ImageProcessor {
    public static String encodeImage(String photoURL) {
        try {
            String extension = photoURL.substring(photoURL.lastIndexOf("."));
            byte[] imageByteArray = Files.readAllBytes(Path.of(photoURL));

            return encodeImageHelper(imageByteArray, extension);
        } catch (IOException e) {
            return "";
        }
    }

    private static String encodeImageHelper(byte[] imageByteArray, String fileType) {
        return "data:" + fileType + ";base64," + Base64.getEncoder().encodeToString(imageByteArray);
    }
}
