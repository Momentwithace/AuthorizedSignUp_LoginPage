package com.ace.authorizereg_loginpage.registration;

public class EmailNotValidException extends RuntimeException {
    public EmailNotValidException(String message) {
        super(message);
    }
}
