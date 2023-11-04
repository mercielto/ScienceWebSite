package com.example.webprojectscience.exceptions;

import java.rmi.ServerException;

public class UserHasNoAccessException extends ServerException {
    public UserHasNoAccessException(String s) {
        super(s);
    }

    public UserHasNoAccessException(String s, Exception ex) {
        super(s, ex);
    }
}
