package com.project.TasteTreasure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.TasteTreasure.classes.Contact;
import com.project.TasteTreasure.repositories.ContactRepository;

@Controller
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/contact")
    public String getContactPage() {
        return "contact";
    }

    @PostMapping("processContact")
    public String processContact(@ModelAttribute Contact contact,
                                RedirectAttributes redirectAttributes) {

        try {
            contactRepository.saveContact(contact);
            redirectAttributes.addFlashAttribute("successMessage", "Aye, ye scurvy dog! Yer missive has been delivered to the crow's nest, and we'll be sendin' a reply on the next tide. Keep a weather eye on your inbox!");

        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "Avast, ye matey! Thar be an error on the horizon! But fear not, for we'll be patchin' things up with the help of our trusty crew!");

        }

        return "redirect:/";
    }
}
