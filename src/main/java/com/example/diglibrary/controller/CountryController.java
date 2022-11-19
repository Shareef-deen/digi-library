package com.example.diglibrary.controller;

import com.example.diglibrary.exception.AuthorNotFoundException;
import com.example.diglibrary.exception.CountryNotFoundException;
import com.example.diglibrary.model.Author;
import com.example.diglibrary.model.Country;
import com.example.diglibrary.repository.CountryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class CountryController {
    @Autowired
    private CountryRepository countryRepository;
    ObjectMapper objectMapper = new ObjectMapper();

    Country country1 ;


    @GetMapping
    public void autoAdd(String country) throws IOException{
        String url= "https://restcountries.com/v2/alpha/"+country;
        RestTemplate restTemplate = new RestTemplate();

        String countries = restTemplate.getForObject(url, String.class);
        System.out.println(countries);

        JsonNode node = objectMapper.readTree(countries);



        List<String> tim = new ArrayList<>();
        if(node.get("timezones").isArray()){
            for(JsonNode jsonNode: node.get("timezones")){
                tim.add(jsonNode.textValue());
            }
        }
        String[] timarr= new String[tim.size()];
        tim.toArray(timarr);
        StringBuffer sb = new StringBuffer();
        for(int i =0; i < timarr.length;i++){
            sb.append(timarr[i]+", ");
        }
        String str =sb.toString();
        System.out.println("First "+str);

        String nam=node.get("name").textValue();
        String curr=node.get("currencies").textValue();
        String alph=node.get("alpha3Code").textValue();
        String lang=node.get("languages").findValue("name").textValue();




       //Country count = new Country(alph,nam,str,curr,lang);
       //newAutoAdd(count);



    }
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
