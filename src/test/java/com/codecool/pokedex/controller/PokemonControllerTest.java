package com.codecool.pokedex.controller;

import com.codecool.pokedex.repository.pokemon.PokemonRepository;
import com.codecool.pokedex.model.pokemon.Ability;
import com.codecool.pokedex.model.pokemon.Pokemon;
import com.codecool.pokedex.model.pokemon.Type;
import com.codecool.pokedex.service.PokemonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(PokemonController.class)
@ExtendWith(MockitoExtension.class)
class PokemonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PokemonService pokemonService;

    @MockBean
    private PokemonRepository pokemonRepository;

    /*Controller test*/

    @Test
    @SneakyThrows
    void getPokemonsReturnProperly() {
        String url = "/";
        String contentType = "application/json";

        mockMvc.perform(get(url)
                .contentType(contentType))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void getTypesReturnProperly() {
        String url = "/types";
        String contentType = "application/json";

        mockMvc.perform(get(url)
                .contentType(contentType))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @SneakyThrows
    void getAbilitiesReturnProperly() {
        String url = "/abilities";
        String contentType = "application/json";

        mockMvc.perform(get(url)
                .contentType(contentType))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @SneakyThrows
    void getPokemonWithValidId() {
        int id = 1;
        mockMvc.perform(get("/pokemon/{id}", id)
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @SneakyThrows
    @Disabled
    void getPokemonWithInvalidId() {
        int id = -3;
        mockMvc.perform(get("/pokemon/{id}", id)
                .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @SneakyThrows
    void getPokemonWithNullId() {
        Integer id = null;
        mockMvc.perform(get("/pokemon/{id}", id)
                .contentType("application/json"))
                .andExpect(status().is(404));
    }

    @Test
    @SneakyThrows
    void getPokemonsByTypeReturnProperly() {
        String name = "name";
        String url = "url";
        Type type = new Type(name, url);
        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post("/type/{type}", name)
                .content(mapper.writeValueAsString(type))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @SneakyThrows
    void getPokemonsByAbilityReturnProperly() {
        String name = "name";
        String url = "url";
        Ability ability = new Ability(name, url);
        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post("/ability/{name}", name)
                .content(mapper.writeValueAsString(ability))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    /*Business logic test*/

    @Test
    void getPokemonsInvokesService() {
        List<Pokemon> pokemons = new ArrayList<>();
        int limit = 5;
        doReturn(pokemons).when(pokemonService).getPokemons(limit);
        assertEquals(pokemons, pokemonService.getPokemons(limit));
    }

    @Test
    void getPokemonInvokesService() {
        int id = 1;
        Optional<Pokemon> pokemon = Optional.of(Pokemon.builder().id(1).build());

        doReturn(pokemon).when(pokemonService).getPokemon(id);
        assertEquals(pokemon, pokemonService.getPokemon(id));
    }

    @Test
    void getTypesInvokesService() {
        List<Type> types = new ArrayList<>();
        doReturn(types).when(pokemonService).getTypes();
        assertEquals(types, pokemonService.getTypes());
    }

    @Test
    void getAbilitiesInvokesService() {
        List<Ability> abilities = new ArrayList<>();
        doReturn(abilities).when(pokemonService).getAbilities();
        assertEquals(abilities, pokemonService.getAbilities());
    }

    @Test
    void getPokemonsByTypeInvokesService() {
        Type type = new Type();
        List<Pokemon> pokemons = new ArrayList<>();

        doReturn(pokemons).when(pokemonService).getPokemonsByType(type);
        assertEquals(pokemons, pokemonService.getPokemonsByType(type));
    }

    @Test
    void getPokemonsByAbilityInvokesService() {
        Ability ability = new Ability();
        List<Pokemon> pokemons = new ArrayList<>();

        doReturn(pokemons).when(pokemonService).getPokemonsByAbility(ability);
        assertEquals(pokemons, pokemonService.getPokemonsByAbility(ability));
    }
}