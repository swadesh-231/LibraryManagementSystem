package com.project.librarymanagement.exception;

public class APIException extends RuntimeException{
    public APIException(String message) {
        super(message);
    }
    public APIException() {
    }

}
