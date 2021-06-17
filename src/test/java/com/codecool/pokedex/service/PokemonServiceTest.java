package com.codecool.pokedex.service;

import com.codecool.pokedex.dao.pokemon.PokemonRepository;
import com.codecool.pokedex.model.pokemon.Ability;
import com.codecool.pokedex.model.pokemon.Pokemon;
import com.codecool.pokedex.model.pokemon.Type;
import org.checkerframework.checker.units.qual.A;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DataJpaTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class PokemonServiceTest {

    @Mock
    private PokemonRepository mockRepository;
    private PokemonService pokemonService;

    @BeforeEach
    void setUp() {
        pokemonService = new PokemonService(mockRepository);
    }

    @Test
    void getPokemonsInvokesProperly() {
        pokemonService.getPokemons();
        verify(mockRepository).findAll();
    }

    @Test
    void addPokemonInvokesProperly() {
        Pokemon pokemon = new Pokemon();
        pokemonService.addPokemon(pokemon);
        verify(mockRepository).save(pokemon);
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