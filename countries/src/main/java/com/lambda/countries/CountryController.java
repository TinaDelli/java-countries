package com.lambda.countries;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/names")
public class CountryController
{
    //localhost:2018/names/all
    @RequestMapping(value = "/all",
                    produces = {"application/json"})
    public ResponseEntity<?> getAllCountries()
    {
        CountriesApplication.ourCountryList.countryList.sort((c1, c2) -> (int)(c1.getPopulation() - c2.getPopulation()));
        return new ResponseEntity<>(CountriesApplication.ourCountryList.countryList, HttpStatus.OK);
    }

    //localhost:2018/names/start/{letter}
    @GetMapping(value="start/{letter}",
                produces = {"application/json"})
    public ResponseEntity<?> getCountries(
            @PathVariable
                char letter)
    {
        ArrayList<Country> rtnCountries = CountriesApplication.ourCountryList.findCountries(c -> c.getName().toUpperCase().charAt(0) == Character.toUpperCase(letter));
        return new ResponseEntity<>(rtnCountries, HttpStatus.OK);
    }

    //localhost:2018/names/size/{number}
    @GetMapping(value="size/{number}",
                produces = {"application/json"})
    public ResponseEntity<?> getCountries(
            @PathVariable
                    int number)
    {
        ArrayList<Country> rtnCountries = CountriesApplication.ourCountryList.findCountries(c -> (c.getName().length() == number));
        return new ResponseEntity<>(rtnCountries, HttpStatus.OK);
    }

}
