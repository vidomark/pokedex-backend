package com.codecool.pokedex.datalayer.dao;

import com.codecool.pokedex.model.Pokemon;
import java.util.List;

public interface PokemonRepository {

    List<Pokemon> getPokemons();

    boolean addPokemon(Pokemon pokemon);
}
