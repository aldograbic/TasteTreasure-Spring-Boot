package com.project.TasteTreasure.repositories.country;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.TasteTreasure.classes.Country;

@Repository
public class JdbcCountryRepository implements CountryRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcCountryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Country> getAllCountries() {
        String sql = "SELECT id, code, name FROM countries";
        return jdbcTemplate.query(sql, new CountryRowMapper());
    }

    @Override
    public Country getCountryById(int countryId) {
        String sql = "SELECT id, code, name FROM countries WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, new CountryRowMapper(), countryId);
    }

    @Override
    public int getCountryIdByName(String countryName) {
        String sql = "SELECT id FROM countries WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, countryName);
    }

    @Override
    public String getCountryCodeById(int countryId) {
        String sql = "SELECT code FROM countries WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, String.class, countryId);
    }
}
