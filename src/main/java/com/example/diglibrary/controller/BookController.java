package com.example.diglibrary.controller;

import com.example.diglibrary.exception.AuthorNotFoundException;
import com.example.diglibrary.exception.BookNotFoundException;
import com.example.diglibrary.model.Author;
import com.example.diglibrary.model.Book;
import com.example.diglibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookRepository bookRepository ;

    @PostMapping("/book")
    Book newBook(@RequestBody Book newBook){
        return bookRepository.save(newBook);
    }
    @GetMapping("/books")
    List<Book> getAllBooks(){

        return bookRepository.findAll();
    }
    @GetMapping("/book/{id}")
    Book getBook(@PathVariable String id){
        return bookRepository.findById(id)
                .orElseThrow(()-> new BookNotFoundException(id));
    }
    @PutMapping("/book/{id}")
    Book updateBook(@RequestBody Book newBook, @PathVariable String id){
        return bookRepository.findById(id)
                .map(author -> {
                    author.setAuthor(newBook.getAuthor());
                    author.setDate(newBook.getDate());
                    author.setGenres(newBook.getGenres());
                    author.setIsbn(newBook.getIsbn());
                    author.setTitle(newBook.getTitle());
                    author.setAuthor(newBook.getAuthor());
                    return bookRepository.save(author);
                }).orElseThrow(()-> new BookNotFoundException(id));
    }
    @DeleteMapping("/book/{id}")
    String deleteBook(@PathVariable String id){
        if(!bookRepository.existsById(id)){
            throw new BookNotFoundException(id);
        }
        else {
            bookRepository.deleteById(id);
            return "book has been deleted";
        }

    }
}
