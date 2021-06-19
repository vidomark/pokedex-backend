package com.codecool.pokedex.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class IndexController {

    @GetMapping
    public  ModelAndView index () {
        return new ModelAndView("index.html");
    }
}
