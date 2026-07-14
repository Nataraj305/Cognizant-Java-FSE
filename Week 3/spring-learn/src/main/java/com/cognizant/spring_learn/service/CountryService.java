package com.cognizant.spring_learn.service;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.cognizant.spring_learn.model.Country;

@Service
public class CountryService {

    private List<Country> countryList;

    @SuppressWarnings("unchecked")
    public CountryService() {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("country.xml");

        countryList = (List<Country>) context.getBean("countryList");
    }

    public Country getCountry() {

        return countryList.get(0);
    }

    public List<Country> getAllCountries() {

        return countryList;
    }

    public Country getCountry(String code) {

        for (Country country : countryList) {

            if (country.getCode().equalsIgnoreCase(code)) {
                return country;
            }
        }

        return null;
    }
}