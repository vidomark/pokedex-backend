package com.codecool.pokedex.dao.pokemon;

import com.codecool.pokedex.dao.pokemon.util.PokemonUtil;
import com.codecool.pokedex.model.pokemon.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class PokemonRepositoryMem implements PokemonRepository {

    private final Set<Pokemon> pokemons = new LinkedHashSet<>();

    public PokemonRepositoryMem() throws IOException {
        createPokemons();
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

    @Override
    public Set<Stat> getProperties() {
        return pokemons
                .stream()
                .map(Pokemon::getStats)
                .flatMap(List::stream)
                .map(StatsItem::getStat)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private void createPokemons() throws IOException {
        String endpoint = "https://pokeapi.co/api/v2/pokemon?offset=0&limit=60";
        JSONObject data = PokemonUtil.fetchData(endpoint);
        JSONArray pokemonJsonArray = data.getJSONArray("results");
        pokemonJsonArray.forEach(pokemonData -> {
            JSONObject pokemonJson = (JSONObject) pokemonData;
            String pokemonUrl = pokemonJson.getString("url");
            try {
                Pokemon pokemon = PokemonUtil.fetchPokemon(pokemonUrl);
                pokemons.add(pokemon);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });
    }
}
