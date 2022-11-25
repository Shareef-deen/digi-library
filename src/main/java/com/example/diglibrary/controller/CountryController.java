package com.example.diglibrary.controller;

import com.example.diglibrary.exception.CountryNotFoundException;
import com.example.diglibrary.model.Count;
import com.example.diglibrary.model.Country;
import com.example.diglibrary.repository.CountRepository;
import com.example.diglibrary.repository.CountryRepository;
import com.example.diglibrary.service.AutoAddCountry;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class CountryController {
    @Autowired
    private CountryRepository countryRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private CountRepository countRepository;

    @Autowired
    private AutoAddCountry autoAddCountry;



    @PostMapping
    Country newAutoAdd(@RequestBody Country newCountry){
        return countryRepository.save(newCountry);
    }
    @PostMapping("/country")
    Country newCountry(@RequestBody Country newCountry) {
        return countryRepository.save(newCountry);
    }
    @GetMapping("/countries")
    List<Country> getAllCountries(){

        return countryRepository.findAll();
    }
    @GetMapping("/country/{id}")
    Country getcountry(@PathVariable String id){
        return countryRepository.findById(id)
                .orElseThrow(()-> new CountryNotFoundException(id));
    }
    @PutMapping("/country/{id}")
    Country updateCountry(@RequestBody Country newCountry, @PathVariable String id){
        return countryRepository.findById(id)
                .map(author-> {
                    author.setCurrencies(newCountry.getCurrencies());
                    author.setLanguages(newCountry.getLanguages());
                    author.setTimezones(newCountry.getTimezones());
                    author.setName(newCountry.getName());
                    author.setAlpha3Code(newCountry.getAlpha3Code());
                    return countryRepository.save(author);
                }).orElseThrow(()-> new CountryNotFoundException(id));
    }
    @DeleteMapping("/country/{id}")
    String deleteCountry(@PathVariable String id){
        if(!countryRepository.existsById(id)){
            throw new CountryNotFoundException(id);
        }
        else {
            countryRepository.deleteById(id);
            return "country has been deleted";
        }

    }

}
