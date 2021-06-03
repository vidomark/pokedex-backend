package com.codecool.pokedex.controller;

import com.codecool.pokedex.model.pokemon.Ability;
import com.codecool.pokedex.model.pokemon.Pokemon;
import com.codecool.pokedex.model.pokemon.Stat;
import com.codecool.pokedex.model.pokemon.Type;
import com.codecool.pokedex.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Set;

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
    public Set<Pokemon> getPokemons() throws IOException {
        return pokemonService.getPokemons();
    }

    @GetMapping(path = "pokemon/{id}")
    public Pokemon getPokemon(@PathVariable("id") int id) {
        return pokemonService.getPokemon(id);
    }

    @GetMapping(path = "types")
    public Set<Type> getTypes() {
        return pokemonService.getTypes();
    }

    @GetMapping(path = "abilities")
    public Set<Ability> getAbilities() {
        return pokemonService.getAbilities();
    }

    @PostMapping(path = "type/{type}")
    public Set<Pokemon> getPokemonsByType(@PathVariable("type") String typeName, @RequestBody Type type) {
        return pokemonService.getPokemonsByType(type);
    }

    @PostMapping(path = "ability/{name}")
    public Set<Pokemon> getPokemonsByAbility(@PathVariable("name") String abilityName, @RequestBody Ability ability) {
        return pokemonService.getPokemonsByAbility(ability);
    }
}
