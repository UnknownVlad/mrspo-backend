package com.example.mrspobacked.exceptions;

public class UserNotFoundOrPasswordNotMatchException extends RuntimeException {

    public UserNotFoundOrPasswordNotMatchException(String message) {
        super(message);
    }

}
