package com.example.diglibrary.controller;

import com.example.diglibrary.exception.AuthorNotFoundException;
import com.example.diglibrary.model.Author;
import com.example.diglibrary.repository.AuthorRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;
    private String country ;

    @PostMapping("/author")
    Author newAuthor(@RequestBody Author newAuthor) throws IOException{
        country = newAuthor.getCountry();
        CountryController countryController =new CountryController();
        countryController.autoAdd(country);
        System.out.println(country);
        return authorRepository.save(newAuthor);
    }
    @GetMapping("/authors")
    List<Author> getAllAuthors(){

        return authorRepository.findAll();
    }
    @GetMapping("/author/{id}")
    Author getAuthor(@PathVariable Long id){
        return authorRepository.findById(id)
                .orElseThrow(()-> new AuthorNotFoundException(id));
    }
    @PutMapping("/author/{id}")
    Author updateAuthor(@RequestBody Author newAuthor, @PathVariable Long id){
        return authorRepository.findById(id)
                .map(author -> {
                    author.setAge(newAuthor.getAge());
                    author.setCountry(newAuthor.getCountry());
                    author.setFirstname(newAuthor.getFirstname());
                    author.setGenre(newAuthor.getGenre());
                    author.setSecondname(newAuthor.getSecondname());
                    author.setNobooks(newAuthor.getNobooks());
                    return authorRepository.save(author);
                }).orElseThrow(()-> new AuthorNotFoundException(id));
    }
    @DeleteMapping("/author/{id}")
    String deleteAuthor(@PathVariable Long id){
        if(!authorRepository.existsById(id)){
            throw new AuthorNotFoundException(id);
        }
        else {
            authorRepository.deleteById(id);
            return "user has been deleted";
        }

    }
}
