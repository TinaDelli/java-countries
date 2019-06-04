package com.lambda.countries;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@RestController
@RequestMapping("/population")
public class PopulationController
{
    //localhost:2018/population/size/{people}
    @GetMapping(value = "/size/{people}",
                produces = {"application/json"})
    public ResponseEntity<?> checkPopulation(
            @PathVariable
                    int people)
    {
        ArrayList<Country> rtnCountries = CountriesApplication.ourCountryList.findCountries(c -> (c.getPopulation() >= people));
        return new ResponseEntity<>(rtnCountries, HttpStatus.OK);
    }

    //localhost:2018/population/min
    @GetMapping(value = "/min",
                produces = {"application/json"})
    public ResponseEntity<?>getPopMin()
    {
        Country tempCountry = new Country(CountriesApplication.ourCountryList.findCountry(c -> c.getPopulation() == 95702));
        return new ResponseEntity<>(tempCountry, HttpStatus.OK);
    }


    //localhost:2018/population/max
    @GetMapping(value = "/max",
                produces = {"application/json"})
    public ResponseEntity<?>getPopMax()
    {
        Country tempCountry = new Country(CountriesApplication.ourCountryList.findCountry(c -> c.getPopulation() >= c.getPopulation()));
        return new ResponseEntity<>(tempCountry, HttpStatus.OK);
    }

}

