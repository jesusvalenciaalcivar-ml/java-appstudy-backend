package com.jesus.java_app_backend.exception;

public class CredencialesInvalidasException extends RuntimeException{
    public CredencialesInvalidasException(String message){
        super(message);
    }
}
