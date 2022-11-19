package com.example.diglibrary.exception;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String id) {
        super("could not find the said user");
    }
}
