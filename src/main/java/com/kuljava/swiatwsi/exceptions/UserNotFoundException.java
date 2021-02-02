package com.kuljava.swiatwsi.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("User with given username can't be found");
    }
}
