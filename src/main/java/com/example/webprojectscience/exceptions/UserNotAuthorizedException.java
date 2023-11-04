package com.example.webprojectscience.exceptions;

import java.rmi.ServerException;

public class UserNotAuthorizedException extends ServerException {
    public UserNotAuthorizedException(String s) {
        super(s);
    }

    public UserNotAuthorizedException(String s, Exception ex) {
        super(s, ex);
    }
}
