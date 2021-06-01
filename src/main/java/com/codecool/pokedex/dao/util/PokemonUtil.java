package com.codecool.pokedex.dao.util;

import com.codecool.pokedex.model.Pokemon;
import com.google.gson.Gson;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class PokemonUtil {
    private static URL url;

    public static JSONObject fetchData(String endpoint) throws IOException {
        url = new URL(endpoint);
        URLConnection connection = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
        String lines = reader.lines().collect(Collectors.joining("\n"));
        reader.close();
        JSONObject json = new JSONObject(lines);
        return json;
    }

    public static Pokemon fetchPokemon(String endpoint) throws IOException {
        url = new URL(endpoint);
        URLConnection connection = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
        String lines = reader.lines().collect(Collectors.joining("\n"));
        reader.close();
        return new Gson().fromJson(lines, Pokemon.class);
    }
}
