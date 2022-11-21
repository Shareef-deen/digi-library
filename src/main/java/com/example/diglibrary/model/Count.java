package com.example.diglibrary.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Count {
    @Id
    private String alpha3Code;
    private String name;
    private String[] timezones;

    private Currencies[] currencies;

    //private String[] languages;

    private Languages[] languages;

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getTimezones() {
        return timezones;
    }

    public void setTimezones(String[] timezones) {
        this.timezones = timezones;
    }

    public Languages[] getLanguages() {
        return languages;
    }

    public void setLanguages(Languages[] languages) {
        this.languages = languages;
    }

    public Currencies[] getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Currencies[] currencies) {
        this.currencies = currencies;
    }
/*public String[] getCurrencies() {
        return currencies;
    }

    public void setCurrencies(String[] currencies) {
        this.currencies = currencies;
    }

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }*/
}
