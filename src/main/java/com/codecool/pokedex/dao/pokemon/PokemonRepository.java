package com.codecool.pokedex.dao.pokemon;

import com.codecool.pokedex.model.Pokemon;
import java.util.List;

public interface PokemonRepository {

    List<Pokemon> getPokemons();

    boolean addPokemon(Pokemon pokemon);

    Pokemon getPokemon(int id);
}
