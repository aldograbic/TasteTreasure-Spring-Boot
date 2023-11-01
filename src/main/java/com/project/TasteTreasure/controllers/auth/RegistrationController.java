package com.project.TasteTreasure.controllers.auth;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.TasteTreasure.classes.Country;
import com.project.TasteTreasure.classes.User;
import com.project.TasteTreasure.config.EmailService;
import com.project.TasteTreasure.repositories.country.CountryRepository;
import com.project.TasteTreasure.repositories.user.UserRepository;

@Controller
public class RegistrationController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {

        List<Country> countries = countryRepository.getAllCountries();
        model.addAttribute("countries", countries);

        return "registration";
    }

    @PostMapping("processRegistration")
    public String processRegistration(@RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("country") String countryName,
            @RequestParam("email") String email,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("confirmPassword") String confirmPassword,
            RedirectAttributes redirectAttributes) {

        int countryId = countryRepository.getCountryIdByName(countryName);

        User user = new User(firstName, lastName, username, email, password, countryId);

        User existingUserEmail = userRepository.findByEmail(user.getEmail());
        if (existingUserEmail != null) {
            redirectAttributes.addFlashAttribute("errorMessage", "User already exists with the same e-mail address!");
            return "redirect:/registration";
        }

        User existingUserUsername = userRepository.findByUsername(user.getUsername());
        if (existingUserUsername != null) {
            redirectAttributes.addFlashAttribute("errorMessage", "User already exists with the same username!");
            return "redirect:/registration";
        }

        if (!user.getPassword().equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Passwords must be the same!");
            return "redirect:/registration";
        }

        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        String token = UUID.randomUUID().toString();
        user.setConfirmationToken(token);
        user.setEmailVerified(false);

        userRepository.saveUser(user);

        String confirmationLink = "http://localhost:8080/confirm?token=" + token;

        try {

            String to = user.getEmail();
            String subject = "Confirm your e-mail - Taste Treasure";
            String htmlContent = "<html><body><h1>AHOY, " + user.getFirstName()
                    + "!</h1> <p><b>Confirm your e-mail address by clicking on the link below:</br> <a href='"
                    + confirmationLink + "'>Click here!</a></body></html>";
            emailService.sendHtmlEmail(to, subject, htmlContent);

            redirectAttributes.addFlashAttribute("successMessage",
                    "We send yer an e-mail with the instructions in a bottle across the digital see!");

        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Avast, ye matey! Thar be an error on the horizon! But fear not, for we'll be patchin' things up with the help of our trusty crew!");
        }

        return "redirect:/";
    }

    @GetMapping("/confirm")
    public String confirmEmail(@RequestParam("token") String token, RedirectAttributes redirectAttributes) {

        User user = userRepository.findByConfirmationToken(token);

        if (user == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Wrong token for email confirmation, matey!");
            return "redirect:/";
        }

        if (user.isEmailVerified()) {
            redirectAttributes.addFlashAttribute("infoMessage", "Yarr, the email address be confirmed already, matey!");
            return "redirect:/";
        }

        user.setEmailVerified(true);
        userRepository.updateEmailVerification(user);
        redirectAttributes.addFlashAttribute("successMessage", "Ye have successfully confirmed yer email address!");

        return "redirect:/";
    }
}
