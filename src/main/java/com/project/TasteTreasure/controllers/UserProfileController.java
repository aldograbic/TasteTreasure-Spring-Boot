package com.project.TasteTreasure.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserProfileController {

    @GetMapping("/account")
    public String getUserProfilePage() {
        return "user-profile";
    }
}
