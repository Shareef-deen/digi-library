package com.example.diglibrary.repository;


import com.example.diglibrary.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,String> {
}
