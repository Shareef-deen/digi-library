package com.example.diglibrary.exception;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(Long id) {
        super("could not find the said user");
    }
}
