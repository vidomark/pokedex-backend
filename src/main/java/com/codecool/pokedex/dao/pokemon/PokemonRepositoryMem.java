package com.codecool.pokedex.dao.pokemon;

import com.codecool.pokedex.dao.pokemon.util.PokemonContainer;
import com.codecool.pokedex.model.pokemon.*;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class PokemonRepositoryMem implements PokemonRepository {

    private final Set<Pokemon> pokemons;
    private final PokemonContainer pokemonContainer = new PokemonContainer();

    public PokemonRepositoryMem() throws IOException {
        pokemons = pokemonContainer.getPokemons();
    }

    @Override
    public Set<Pokemon> getPokemons() {
        return pokemons;
    }

    @Override
    public boolean addPokemon(Pokemon pokemon) {
        return pokemons.add(pokemon);
    }

    @Override
    public Pokemon getPokemon(int id) {
        return pokemons.stream()
                .filter(pokemon -> pokemon.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public Set<Pokemon> getPokemonsByType(Type type) {
        return pokemons.stream()
                .filter(pokemon -> pokemon.getTypes()
                        .stream()
                        .map(TypesItem::getType)
                        .anyMatch(pokemonType -> pokemonType.equals(type)))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public Set<Type> getTypes() {
        return pokemons.stream()
                .map(Pokemon::getTypes)
                .flatMap(List::stream)
                .map(TypesItem::getType)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public Set<Ability> getAbilities() {
        return pokemons.stream()
                .map(Pokemon::getAbilities)
                .flatMap(List::stream)
                .map(AbilitiesItem::getAbility)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public Set<Pokemon> getPokemonsByAbility(Ability ability) {
        return pokemons
                .stream()
                .filter(pokemon -> pokemon.getAbilities()
                        .stream()
                        .map(AbilitiesItem::getAbility)
                        .anyMatch(pokemonAbility -> pokemonAbility.equals(ability)))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
