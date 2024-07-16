package com.carbonit.exceptions;

public class InvalidSynthaxException extends Exception {
    public InvalidSynthaxException(String err){
        super(err) ;
    }

    public InvalidSynthaxException(String err, Throwable th){
        super(err,th) ;
    }    
}
