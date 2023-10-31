package com.project.TasteTreasure.repositories.contact;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.TasteTreasure.classes.Contact;

@Repository
public class JdbcContactRepository implements ContactRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcContactRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveContact(Contact contact) {
        String sql = "INSERT INTO messages(name, email, message) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, contact.getName(), contact.getEmail(), contact.getMessage());
    }
}
