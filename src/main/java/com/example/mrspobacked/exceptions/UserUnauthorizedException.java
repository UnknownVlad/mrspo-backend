package com.example.mrspobacked.exceptions;

public class UserUnauthorizedException extends RuntimeException {

    public UserUnauthorizedException(String message) {
        super(message);
    }

}