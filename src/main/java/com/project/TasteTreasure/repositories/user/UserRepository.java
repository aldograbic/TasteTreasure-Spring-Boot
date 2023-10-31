package com.project.TasteTreasure.repositories.user;

import com.project.TasteTreasure.classes.User;

public interface UserRepository {

    void saveUser(User user);
    User findByUsername(String username);
    User findByEmail(String email);
}
