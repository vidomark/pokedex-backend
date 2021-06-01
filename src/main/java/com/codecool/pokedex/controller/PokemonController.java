package com.codecool.pokedex.controller;

import com.codecool.pokedex.model.Pokemon;
import com.codecool.pokedex.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

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
    public List<Pokemon> getPokemons() throws IOException {
        return pokemonService.getPokemons();
    }

    @GetMapping(path = "{id}")
    public Pokemon getPokemon(@PathVariable("id") int id) {
        return pokemonService.getPokemon(id);
    }
}
