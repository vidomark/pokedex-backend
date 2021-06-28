package com.codecool.pokedex.util;

import com.codecool.pokedex.model.pokemon.Pokemon;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PokemonContainer { // for database creation only

    private final int pokemonNumber;
    private String endpoint; // 100 pokemons
    private JSONObject data;
    private JSONArray pokemonJsonArray;
    private final List<Pokemon> pokemons = new ArrayList<>();

    public PokemonContainer(int pokemonNumber) throws IOException {
        this.pokemonNumber = pokemonNumber;
        this.endpoint = String.format( "https://pokeapi.co/api/v2/pokemon?limit=%s", pokemonNumber);
        this.data = UrlReader.fetchData(endpoint);
        this.pokemonJsonArray =  data.getJSONArray("results");
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
                Pokemon pokemon = UrlReader.fetchPokemon(pokemonUrl);
                pokemons.add(pokemon);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });
    }
}
