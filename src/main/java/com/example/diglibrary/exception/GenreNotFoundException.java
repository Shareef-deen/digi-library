package com.example.diglibrary.exception;

public class GenreNotFoundException extends RuntimeException{
    public GenreNotFoundException(String id) {
        super("could not find the said user");
    }
}
