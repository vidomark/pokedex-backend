package com.codecool.pokedex.dao.pokemon;

import com.codecool.pokedex.model.pokemon.Ability;
import com.codecool.pokedex.model.pokemon.Pokemon;
import com.codecool.pokedex.model.pokemon.Type;

import java.util.List;
import java.util.Set;

public interface PokemonRepository {

    Set<Pokemon> getPokemons();

    boolean addPokemon(Pokemon pokemon);

    Pokemon getPokemon(int id);

    Set<Pokemon> getPokemonsByType(Type type);

    Set<Type> getTypes();

    Set<Ability> getAbilities();

    Set<Pokemon> getPokemonsByAbility(Ability ability);
}
