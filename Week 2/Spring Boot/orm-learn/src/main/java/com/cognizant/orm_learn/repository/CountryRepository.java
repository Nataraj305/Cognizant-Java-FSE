package com.cognizant.orm_learn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.orm_learn.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    // Get countries containing the given text
    List<Country> findByNameContaining(String text);

    // Get countries containing the given text in ascending order
    List<Country> findByNameContainingOrderByNameAsc(String text);

    // Get countries starting with the given alphabet
    List<Country> findByNameStartingWith(String alphabet);

}

