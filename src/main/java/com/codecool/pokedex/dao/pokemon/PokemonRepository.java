package com.codecool.pokedex.dao.pokemon;

import com.codecool.pokedex.model.pokemon.Pokemon;
import com.codecool.pokedex.model.pokemon.Type;

import java.util.List;

public interface PokemonRepository {

    List<Pokemon> getPokemons();

    boolean addPokemon(Pokemon pokemon);

    Pokemon getPokemon(int id);

    List<Pokemon> getPokemonsByType(Type type);
}
