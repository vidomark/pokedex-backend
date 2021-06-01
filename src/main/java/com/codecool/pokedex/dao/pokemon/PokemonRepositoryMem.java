package com.codecool.pokedex.dao.pokemon;

import com.codecool.pokedex.dao.pokemon.util.PokemonUtil;
import com.codecool.pokedex.model.pokemon.Pokemon;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Pokemon getPokemon(int id) {
        return pokemons.stream()
                .filter(pokemon -> pokemon.getId() == id)
                .findAny()
                .orElse(null);
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
