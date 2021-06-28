package com.codecool.pokedex.util;

import com.codecool.pokedex.model.pokemon.Pokemon;
import com.google.gson.Gson;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class UrlReader {

    public static JSONObject fetchData(String endpoint) throws IOException {
        String jsonString = getJsonString(endpoint);
        return new JSONObject(jsonString);
    }

    public static Pokemon fetchPokemon(String endpoint) throws IOException {
        String lines = getJsonString(endpoint);
        return new Gson().fromJson(lines, Pokemon.class);
    }

    private static String getJsonString(String endpoint) throws IOException {
        URL url = new URL(endpoint);
        URLConnection connection = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
        String jsonString = reader.lines().collect(Collectors.joining("\n"));
        reader.close();
        return jsonString;
    }
}
