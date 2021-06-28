package com.codecool.pokedex.controller;

import com.codecool.pokedex.service.registration.RegistrationRequest;
import com.codecool.pokedex.service.registration.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }
}
