package com.codecool.pokedex.dao.pokemon.util;

import com.codecool.pokedex.model.pokemon.Pokemon;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PokemonContainer { // for database creation only

    private final String ENDPOINT =  "https://pokeapi.co/api/v2/pokemon?limit=200"; // 100 pokemons
    private final JSONObject data = PokemonUtil.fetchData(ENDPOINT);
    private final JSONArray pokemonJsonArray = data.getJSONArray("results");
    private final List<Pokemon> pokemons = new ArrayList<>();

    public PokemonContainer() throws IOException {
        createPokemons();
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    private void createPokemons() {
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
