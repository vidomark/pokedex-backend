package com.codecool.pokedex.service;

import com.codecool.pokedex.dao.pokemon.PokemonRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class PokemonServiceTest {

    @Mock
    private PokemonRepository mockRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void savePokemon() {
    }

    @Test
    void getPokemons() {
    }

    @Test
    void addPokemon() {
    }

    @Test
    void getPokemon() {
    }

    @Test
    void getPokemonsByType() {
    }

    @Test
    void getTypes() {
    }

    @Test
    void getAbilities() {
    }

    @Test
    void getPokemonsByAbility() {
    }
}