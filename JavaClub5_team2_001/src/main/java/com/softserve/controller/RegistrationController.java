package com.softserve.controller;

import java.util.logging.Logger;

import javax.validation.Valid;

import com.softserve.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softserve.entity.User;
import com.softserve.service.UserService;


@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserService userService;

    private Logger logger = Logger.getLogger(getClass().getName());

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/showRegistrationForm")
    public String showMyLoginPage(Model theModel) {
        theModel.addAttribute("userValidator", new UserValidator());
        return "registration-form";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm(
            @Valid @ModelAttribute("userValidator")
                   UserValidator theUserValidator,
                   BindingResult theBindingResult,
                   Model theModel) {
        String userEmail = theUserValidator.getEmail();
        logger.info("Processing registration form for: " + userEmail);
        if (theBindingResult.hasErrors()) {
            return "registration-form";
        }

        User existing = userService.findByUserEmail(userEmail);
        if (existing != null) {
            theModel.addAttribute("userValidator", new UserValidator());
            theModel.addAttribute("registrationError", "User name already exists.");

            logger.warning("User name already exists.");
            return "registration-form";
        }

        userService.save(theUserValidator);
        logger.info("Successfully created user: " + userEmail);
        return "registration-confirmation";
    }

}
