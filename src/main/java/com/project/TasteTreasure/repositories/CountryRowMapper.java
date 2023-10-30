package com.project.TasteTreasure.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.project.TasteTreasure.classes.Country;

public class CountryRowMapper implements RowMapper<Country> {

    @Override
    public Country mapRow(ResultSet rs, int rowNum) throws SQLException {

        Country country = new Country();
        country.setId(rs.getInt("id"));
        country.setCode(rs.getString("code"));
        country.setName(rs.getString("name"));

        return country;
    }

}
