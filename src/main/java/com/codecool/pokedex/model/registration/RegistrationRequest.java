package com.codecool.pokedex.model.registration;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationRequest {

    private final String firstName;
    private final String lastName;
    private final String username;
    private final String email;
    private final String password;
}
