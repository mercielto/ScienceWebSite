package com.example.webprojectscience.utill;

import java.util.UUID;

public class Generator {
    public static String generateToken() {
        return UUID.randomUUID().toString();
    }
}
