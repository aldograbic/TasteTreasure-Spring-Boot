package com.project.TasteTreasure.repositories.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.project.TasteTreasure.classes.Country;
import com.project.TasteTreasure.classes.User;
import com.project.TasteTreasure.repositories.country.CountryRepository;

public class UserRowMapper implements RowMapper<User> {

    private final CountryRepository countryRepository;

    public UserRowMapper(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setCountryId(rs.getInt("country_id"));

        int countryId = rs.getInt("country_id");
        Country country = countryRepository.getCountryById(countryId);
        if (country != null) {
            user.setCountry(country);
        } else {
            // Handle the case where the country is not found
            // You can log a message or take appropriate action
        }

        return user;
    }
}
