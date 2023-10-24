package com.project.TasteTreasure.controllers.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistrationController {

    @GetMapping("/registration")
    public String getRegistrationPage() {
        return "registration";
    }
    
}
