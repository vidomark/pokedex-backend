package com.codecool.pokedex.dao;

import com.codecool.pokedex.dao.PokemonRepository;
import com.codecool.pokedex.model.Pokemon;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PokemonRepositoryMem implements PokemonRepository {

    private final List<Pokemon> pokemons = new ArrayList<>();

    @Override
    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    @Override
    public boolean addPokemon(Pokemon pokemon) {
        return pokemons.add(pokemon);
    }
}
