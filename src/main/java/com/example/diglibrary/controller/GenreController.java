package com.example.diglibrary.controller;

import com.example.diglibrary.exception.AuthorNotFoundException;
import com.example.diglibrary.exception.GenreNotFoundException;
import com.example.diglibrary.model.Author;
import com.example.diglibrary.model.Genre;
import com.example.diglibrary.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class GenreController {
    @Autowired
    private GenreRepository genreRepository;

    @PostMapping("/genre")
    Genre newGenre(@RequestBody Genre newGenre){
        return genreRepository.save(newGenre);
    }
    @GetMapping("/genres")
    List<Genre> getAllGenres(){

        return genreRepository.findAll();
    }
    @GetMapping("/genre/{id}")
    Genre getGenre(@PathVariable String id){
        return genreRepository.findById(id)
                .orElseThrow(()-> new GenreNotFoundException(id));
    }
    @PutMapping("/genre/{id}")
    Genre updateGenre(@RequestBody Genre newGenre, @PathVariable String id){
        return genreRepository.findById(id)
                .map(author -> {
                    author.setAuthors(newGenre.getAuthors());
                    author.setName(newGenre.getName());
                    author.setBooks(newGenre.getBooks());
                    return genreRepository.save(author);
                }).orElseThrow(()-> new GenreNotFoundException(id));
    }
    @DeleteMapping("/genre/{id}")
    String deleteAuthor(@PathVariable String id){
        if(!genreRepository.existsById(id)){
            throw new GenreNotFoundException(id);
        }
        else {
            genreRepository.deleteById(id);
            return "user has been deleted";
        }

    }
}
