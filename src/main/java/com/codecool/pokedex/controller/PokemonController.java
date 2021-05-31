package com.codecool.pokedex.controller;

import com.codecool.pokedex.datalayer.util.PokemonUtil;
import com.codecool.pokedex.service.PokemonService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class PokemonController {

    private final PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public void getPokemons() throws IOException {
        String endpoint = "https://pokeapi.co/api/v2/pokemon?offset=0&limit=18";
        JSONObject test = PokemonUtil.fetchData(endpoint);
        System.out.println(test.get("results"));
        pokemonService.getPokemons();
    }
}
