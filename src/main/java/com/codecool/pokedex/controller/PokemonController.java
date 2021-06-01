package com.codecool.pokedex.controller;

import com.codecool.pokedex.dao.util.PokemonUtil;
import com.codecool.pokedex.model.Ability;
import com.codecool.pokedex.model.Form;
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
        JSONArray pokemons = data.getJSONArray("results");
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
        List<Ability> abilities = createAbilities(json);
        Form form = createForm(json);
    }

    private List<Ability> createAbilities(JSONObject json) {
        JSONArray abilitiesJson = json.getJSONArray("abilities");
        return abilitiesJson.toList().stream().map(data -> {
            HashMap dataMap = (HashMap) data;
            HashMap<String, String> abilityMap = (HashMap<String, String>) dataMap.get("ability");

            String name = abilityMap.get("name");
            String url = abilityMap.get("url");
            boolean isHidden = (boolean) dataMap.get("is_hidden");
            int slot = (int) dataMap.get("slot");
            return new Ability(name, isHidden, slot, url);
        }).collect(Collectors.toList());
    }

    private Form createForm(JSONObject json) {
        JSONObject formJson = json.getJSONArray("forms").getJSONObject(0);
        String name = formJson.getString("name");
        return Enum.valueOf(Form.class, name.toUpperCase());
    }
}
