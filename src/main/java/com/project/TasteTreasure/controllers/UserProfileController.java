package com.project.TasteTreasure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.TasteTreasure.classes.User;
import com.project.TasteTreasure.repositories.country.CountryRepository;
import com.project.TasteTreasure.repositories.user.UserRepository;

@Controller
public class UserProfileController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CountryRepository countryRepository;

    @GetMapping("/account")
    public String getUserProfilePage(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        model.addAttribute("user", user);

        String countryCode = countryRepository.getCountryCodeById(user.getCountryId());
        model.addAttribute("countryCode", countryCode);

        return "user-profile";
    }
}
