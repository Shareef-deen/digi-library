package com.example.diglibrary.service;

import com.example.diglibrary.model.Count;
import com.example.diglibrary.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.format.ResolverStyle;

@Service
public class AutoAddCountyImp implements AutoAddCountry{

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Count autoAdd(String country) {
        //Count[] response=restTemplate.getForObject("https://restcountries.com/v2/alpha/"+country, Count[].class);
        ResponseEntity<Count> responseEntity=restTemplate.getForEntity("https://restcountries.com/v2/alpha/"+country,Count.class);
        Count counts= responseEntity.getBody();
        System.out.println(counts);

        return counts;
    }
}
