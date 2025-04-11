package com.rapidshine.carwash.washerservice.exceptions;

public class InvalidJwtTokenException extends RuntimeException{
    public InvalidJwtTokenException(String message) {
        super(message);

    }
}
