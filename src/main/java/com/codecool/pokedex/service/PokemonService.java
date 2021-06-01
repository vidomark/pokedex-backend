package com.codecool.pokedex.service;

import com.codecool.pokedex.dao.PokemonRepository;
import com.codecool.pokedex.model.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PokemonService {

    private PokemonRepository pokemonRepository;

    @Autowired
    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public List<Pokemon> getPokemons() {
        return pokemonRepository.getPokemons();
    }

    public boolean addPokemon(Pokemon pokemon) {
        return pokemonRepository.addPokemon(pokemon);
    }
}