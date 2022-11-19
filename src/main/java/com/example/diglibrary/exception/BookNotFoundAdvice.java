package com.example.diglibrary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

public class BookNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,String > exceptionHandler(BookNotFoundException exception){
        Map<String,String> errorMap =new HashMap<>();
        errorMap.put("error Message",exception.getMessage());
        return errorMap;
    }
}
