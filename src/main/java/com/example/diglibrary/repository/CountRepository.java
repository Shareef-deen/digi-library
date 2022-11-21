package com.example.diglibrary.repository;

import com.example.diglibrary.model.Count;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountRepository extends JpaRepository<Count, String> {
}
