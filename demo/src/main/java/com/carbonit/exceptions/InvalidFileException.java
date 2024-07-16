package com.carbonit.exceptions;

public class InvalidFileException extends Exception {
    public InvalidFileException(String error_message){
        super(error_message) ;
    }

    public InvalidFileException(String error_message, Throwable err){
        super(error_message,err) ;
    }   
}
