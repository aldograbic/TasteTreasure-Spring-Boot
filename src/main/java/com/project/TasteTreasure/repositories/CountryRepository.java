package com.project.TasteTreasure.repositories;

import java.util.List;

import com.project.TasteTreasure.classes.Country;

public interface CountryRepository {

    List<Country> getAllCountries();

    Country getCountryById(int countryId);

    int getCountryIdByName(String countryName);
}
