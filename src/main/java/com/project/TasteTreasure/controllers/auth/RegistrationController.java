package com.project.TasteTreasure.controllers.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.TasteTreasure.classes.Country;
import com.project.TasteTreasure.classes.User;
import com.project.TasteTreasure.repositories.CountryRepository;
import com.project.TasteTreasure.repositories.UserRepository;

@Controller
public class RegistrationController {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {

        List<Country> countries = countryRepository.getAllCountries();
        model.addAttribute("countries", countries);

        return "registration";
    }

    @PostMapping("processRegistration")
    public String processRegistration(@RequestParam("firstName") String firstName,
                                @RequestParam("lastName") String lastName,
                                @RequestParam("country") String country,
                                @RequestParam("email") String email,
                                @RequestParam("username") String username,
                                @RequestParam("password") String password,
                                @RequestParam("confirmPassword") String confirmPassword,
                                RedirectAttributes redirectAttributes) {

        int countryId = countryRepository.getCountryIdByName(country);

        User user = new User(firstName, lastName, username, email, password, countryId);

        userRepository.saveUser(user);
        
        return "redirect:/";
    }
}
