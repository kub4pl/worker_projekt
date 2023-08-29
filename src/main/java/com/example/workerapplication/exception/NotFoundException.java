package com.example.workerapplication.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String e){
        super(e);
    }
    public NotFoundException(){
        super();
    }
}
