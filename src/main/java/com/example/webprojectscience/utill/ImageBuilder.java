package com.example.webprojectscience.utill;

import com.example.webprojectscience.config.Params;
import com.example.webprojectscience.models.Theme;
import com.example.webprojectscience.models.User;

public class ImageBuilder {
    public String getProfilePhotoInBytes(User user) {
        return ImageProcessor.encodeImage(Params.storagePath + "profile\\" + user.getProfilePhotoPath());
    }

    public String getThemePhotoInBytes(Theme theme) {
        return ImageProcessor.encodeImage(Params.storagePath + "theme_images\\" + theme.getPicturePath());
    }

    public String getServicePhotoInBytes(String name) {
        return ImageProcessor.encodeImage(Params.storagePath + "service_files\\img\\" + name);
    }
}
