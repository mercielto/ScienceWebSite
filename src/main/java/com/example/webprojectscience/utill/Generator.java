package com.example.webprojectscience.utill;

public class Generator {
    public static int generateToken() {
        return (int) (Math.random() * 1_000_000);
    }
}
