package com.codecool.pokedex.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationRequest {

    private final String email;
    private final String username;
    private final String password;
}
