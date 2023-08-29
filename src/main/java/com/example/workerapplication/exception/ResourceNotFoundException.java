package com.example.workerapplication.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String e){
        super(e);
    }
}
