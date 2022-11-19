package com.example.diglibrary.repository;

import com.example.diglibrary.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, String> {
}
