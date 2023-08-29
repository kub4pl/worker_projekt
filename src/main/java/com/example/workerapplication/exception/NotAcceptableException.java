package com.example.workerapplication.exception;

public class NotAcceptableException extends RuntimeException{

    public NotAcceptableException(String e){
        super(e);
    }
    public NotAcceptableException(){
        super();
    }
}
