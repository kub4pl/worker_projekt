package com.example.workerapplication.exception;

public class SendEmailException extends RuntimeException {
    public SendEmailException(String e) {
        super(e);
    }
}
