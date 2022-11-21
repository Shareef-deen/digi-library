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


    @PostMapping("/api/{country}")
    public Count autoAdd(@PathVariable String country) throws IOException{
        Count response= autoAddCountry.autoAdd(country);
        return countRepository.save(response);
        /*String url= "https://restcountries.com/v2/alpha/"+country;
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

*/

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
