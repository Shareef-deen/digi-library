package com.example.diglibrary.exception;

public class CountryNotFoundException extends RuntimeException{
    public CountryNotFoundException(String id){
        super("could not find said country");
    }
}
