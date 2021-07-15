package com.codecool.pokedex.controller;

import com.codecool.pokedex.config.PokemonLoaderConfiguration;
import com.codecool.pokedex.model.pokemon.Ability;
import com.codecool.pokedex.model.pokemon.Pokemon;
import com.codecool.pokedex.model.pokemon.Type;
import com.codecool.pokedex.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/pokemon")
@Validated
public class PokemonController {

    private final PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping(params = "limit")
    public List<Pokemon> getPokemons(@RequestParam Integer limit) throws IOException {
        return pokemonService.getPokemons(limit);
    }

    @GetMapping(path = "{id}")
    public Pokemon getPokemon(@PathVariable("id")
                                              @Min(1)
                                              @NotNull Integer id) {
        return pokemonService.getPokemon(id).orElse(null);
    }

    @GetMapping(path = "types")
    public List<Type> getTypes() {
        return pokemonService.getTypes();
    }

    @GetMapping(path = "abilities")
    public List<Ability> getAbilities() {
        return pokemonService.getAbilities();
    }

    @PostMapping(params = "type")
    public List<Pokemon> getPokemonsByType(@RequestBody @NotNull Type type) {
        return pokemonService.getPokemonsByType(type);
    }

    @PostMapping(params = "ability")
    public List<Pokemon> getPokemonsByAbility(@RequestBody @NotNull Ability ability) {
        return pokemonService.getPokemonsByAbility(ability);
    }
}
