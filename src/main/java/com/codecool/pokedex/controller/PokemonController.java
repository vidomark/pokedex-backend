package com.codecool.pokedex.controller;

import com.codecool.pokedex.dao.util.PokemonUtil;
import com.codecool.pokedex.model.Ability;
import com.codecool.pokedex.model.Pokemon;
import com.codecool.pokedex.service.PokemonService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class PokemonController {

    private final PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public void getPokemons() throws IOException {
        String endpoint = "https://pokeapi.co/api/v2/pokemon?offset=0&limit=18";
        JSONObject data = PokemonUtil.fetchData(endpoint);
        JSONArray pokemons = (JSONArray) data.get("results");
        pokemons.forEach(pokemonData -> {
            JSONObject pokemonJson = (JSONObject) pokemonData;
            String pokemonUrl = pokemonJson.getString("url");
            try {
                JSONObject pokemon = PokemonUtil.fetchData(pokemonUrl);
                createPokemon(pokemon);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });
        pokemonService.getPokemons();
    }

    private void createPokemon(JSONObject json) {
        String pokemonName = json.getString("name");
        JSONArray abilitiesJson = (JSONArray) json.get("abilities");
        List<Ability> abilities = createAbilities(abilitiesJson);

    }

    private List<Ability> createAbilities(JSONArray abilitiesJson) {
        return abilitiesJson.toList().stream().map(data -> {
            HashMap abilityMap = (HashMap) data;
            HashMap<String, String> abilityData = (HashMap<String, String>) abilityMap.get("ability");

            String name = abilityData.get("name");
            String url = abilityData.get("url");
            boolean isHidden = (boolean) abilityMap.get("is_hidden");
            int slot = (int) abilityMap.get("slot");
            return new Ability(name, isHidden, slot, url);
        }).collect(Collectors.toList());
    }
}
