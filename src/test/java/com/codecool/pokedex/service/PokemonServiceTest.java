package com.codecool.pokedex.service;

import com.codecool.pokedex.repository.PokemonRepository;
import com.codecool.pokedex.model.pokemon.Ability;
import com.codecool.pokedex.model.pokemon.Pokemon;
import com.codecool.pokedex.model.pokemon.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@DataJpaTest
@ActiveProfiles("test")
class PokemonServiceTest {

    @MockBean
    private PokemonRepository mockRepository;
    private PokemonService pokemonService;

    @BeforeEach
    void setUp() {
        pokemonService = new PokemonService(mockRepository);
    }

    @Test
    void getPokemonsInvokesProperly() {
        int limit = 5;
        pokemonService.getPokemons(limit);
        verify(mockRepository).findAll();
    }

    @Test
    void addPokemonWithValidPokemon() {
        Pokemon validPokemon = new Pokemon();
        pokemonService.addPokemon(validPokemon);

        ArgumentCaptor<Pokemon> argumentCaptor = ArgumentCaptor.forClass(Pokemon.class);
        verify(mockRepository).save(argumentCaptor.capture());

        Pokemon capturedPokemon = argumentCaptor.getValue();
        assertThat(capturedPokemon).isEqualTo(validPokemon);
    }

    @Test
    void addPokemonWithNullPokemon() {
        Pokemon invalidPokemon = null;
        pokemonService.addPokemon(invalidPokemon);

        ArgumentCaptor<Pokemon> argumentCaptor = ArgumentCaptor.forClass(Pokemon.class);
        verify(mockRepository).save(argumentCaptor.capture());

        Pokemon capturedPokemon = argumentCaptor.getValue();
        assertThat(capturedPokemon).isEqualTo(invalidPokemon);
    }

    @Test
    void getPokemonWithValidId() {
        int validId = 1;
        pokemonService.getPokemon(validId);
        verify(mockRepository).findById(validId);
    }

    @Test
    void getPokemonWithNullId() {
        Integer invalidId = null;
        assertThrows(NullPointerException.class, () -> pokemonService.getPokemon(invalidId));
    }

    @Test
    void getPokemonsByValidType() {
        Type validType = new Type("valid", "valid");
        pokemonService.getPokemonsByType(validType);
        verify(mockRepository).getPokemonsByType(validType);
    }

    @Test
    void getPokemonsByNullType() {
        Type invalidType = null;
        pokemonService.getPokemonsByType(invalidType);
        verify(mockRepository).getPokemonsByType(invalidType);
    }

    @Test
    void getTypesInvokesProperly() {
        pokemonService.getTypes();
        verify(mockRepository).getTypes();
    }

    @Test
    void getAbilitiesInvokesProperly() {
        pokemonService.getAbilities();
        verify(mockRepository).getAbilities();
    }

    @Test
    void getPokemonsByValidAbility() {
        Ability validAbility = new Ability("valid", "valid");
        pokemonService.getPokemonsByAbility(validAbility);
        verify(mockRepository).getPokemonsByAbility(validAbility);
    }

    @Test
    void getPokemonsByNullAbility() {
        Ability invalidAbility = null;
        pokemonService.getPokemonsByAbility(invalidAbility);
        verify(mockRepository).getPokemonsByAbility(invalidAbility);
    }
}