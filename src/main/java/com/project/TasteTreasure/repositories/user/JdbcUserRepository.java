package com.project.TasteTreasure.repositories.user;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.TasteTreasure.classes.User;
import com.project.TasteTreasure.repositories.country.CountryRepository;

@Repository
public class JdbcUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final CountryRepository countryRepository;

    public JdbcUserRepository(JdbcTemplate jdbcTemplate, CountryRepository countryRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.countryRepository = countryRepository;
    }

    @Override
    public void saveUser(User user) {
        String sql = "INSERT INTO users (first_name, last_name, username, email, password, country_id, email_verified, confirmation_token) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail(),
                user.getPassword(), user.getCountryId(), user.isEmailVerified(), user.getConfirmationToken());
    }
    
    @Override
    public User findByUsername(String username) {
        String sql = "SELECT id, first_name, last_name, username, email, password, country_id, email_verified, confirmation_token FROM users WHERE username = ?";
        List<User> users = jdbcTemplate.query(sql, new UserRowMapper(countryRepository), username);

        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User findByEmail(String email) {
        String sql = "SELECT id, first_name, last_name, username, email, password, country_id, email_verified, confirmation_token FROM users WHERE email = ?";
        List<User> users = jdbcTemplate.query(sql, new UserRowMapper(countryRepository), email);

        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User findByConfirmationToken(String token) {
        String sql = "SELECT id, first_name, last_name, username, email, password, country_id, email_verified, confirmation_token FROM users WHERE confirmation_token = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new UserRowMapper(countryRepository), token);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void updateEmailVerification(User user) {
        String sql = "UPDATE users SET email_verified = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.isEmailVerified(), user.getId());
    }
}
