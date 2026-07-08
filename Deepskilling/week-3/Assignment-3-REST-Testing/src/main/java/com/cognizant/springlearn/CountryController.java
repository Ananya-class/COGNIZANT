package com.cognizant.springlearn.controller;

import java.util.ArrayList;
import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.service.CountryService;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    private CountryService countryService;

    @RequestMapping("/country")
    public Country getCountryIndia() {
        LOGGER.info("START: getCountryIndia");
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Country country = context.getBean("country", Country.class);
        LOGGER.info("END: getCountryIndia");
        return country;
    }

    @GetMapping("/countries")
    @SuppressWarnings("unchecked")
    public ArrayList<Country> getAllCountries() {
        LOGGER.info("START: getAllCountries");
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        LOGGER.info("END: getAllCountries");
        return context.getBean("countryList", ArrayList.class);
    }

    @GetMapping("/countries/{code}")
    public Country getCountry(@PathVariable("code") String code) throws CountryNotFoundException {
        LOGGER.info("START: getCountry validation for {}", code);
        return countryService.getCountry(code);
    }
}
