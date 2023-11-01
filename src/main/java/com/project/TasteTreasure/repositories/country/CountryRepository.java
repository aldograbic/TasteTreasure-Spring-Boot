package com.project.TasteTreasure.repositories.country;

import java.util.List;

import com.project.TasteTreasure.classes.Country;

public interface CountryRepository {

    List<Country> getAllCountries();

    Country getCountryById(int countryId);

    int getCountryIdByName(String countryName);

    String getCountryCodeById(int countryId);
}
