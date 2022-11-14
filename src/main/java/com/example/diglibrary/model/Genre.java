package com.example.diglibrary.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Genre {
    private int authors;
    @Id
    private String name;
    private int books;

    public int getAuthors() {
        return authors;
    }

    public void setAuthors(int authors) {
        this.authors = authors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBooks() {
        return books;
    }

    public void setBooks(int books) {
        this.books = books;
    }
}
