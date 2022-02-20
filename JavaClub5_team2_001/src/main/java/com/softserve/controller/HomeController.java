package com.softserve.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showHome() { return "home"; }

    @GetMapping("/manager")
    public String showManager() { return "manager"; }

    @GetMapping("/systems")
    public String showSystems() { return "systems"; }

}










