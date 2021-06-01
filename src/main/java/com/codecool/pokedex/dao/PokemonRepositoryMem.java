package com.codecool.pokedex.dao;

import com.codecool.pokedex.dao.util.PokemonUtil;
import com.codecool.pokedex.model.Pokemon;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PokemonRepositoryMem implements PokemonRepository {

    private final List<Pokemon> pokemons = new ArrayList<>();

    public PokemonRepositoryMem() throws IOException {
        createPokemons();
    }

    @Override
    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    @Override
    public boolean addPokemon(Pokemon pokemon) {
        return pokemons.add(pokemon);
    }

    private void createPokemons() throws IOException {
        String endpoint = "https://pokeapi.co/api/v2/pokemon?offset=0&limit=18";
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
        /*return pokemonList.toList().stream().map(pokemonData -> {
            JSONObject pokemonJson = (JSONObject) pokemonData;
            String pokemonUrl = pokemonJson.getString("url");
            Pokemon pokemon = null;
            try {
                pokemon = PokemonUtil.fetchPokemon(pokemonUrl);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            return pokemon;
        }).collect(Collectors.toList());*/
    }
}