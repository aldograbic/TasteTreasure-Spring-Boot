package com.project.TasteTreasure.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.TasteTreasure.classes.User;

@Repository
public class JdbcUserRepository implements UserRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveUser(User user) {
        String sql = "INSERT INTO users (first_name, last_name, username, email, password, country_id) " + 
                    "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail(), user.getPassword(), user.getCountryId());
    }
}
