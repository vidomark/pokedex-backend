package com.codecool.pokedex.dao.pokemon.jpa;

import com.codecool.pokedex.dao.pokemon.PokemonRepository;
import com.codecool.pokedex.dao.pokemon.util.PokemonContainer;
import com.codecool.pokedex.model.pokemon.*;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
class PokemonRepositoryTest {

    @Autowired
    private PokemonRepository pokemonRepository;

    @BeforeEach
    public void initializeRepository() {
        List<AbilitiesItem> abilities = new ArrayList<>();
        Ability overgrow = new Ability("overgrow", "https://pokeapi.co/api/v2/ability/65/");
        Ability chlorophyll = new Ability("chlorophyll", "https://pokeapi.co/api/v2/ability/34/");
        AbilitiesItem abilityOne = new AbilitiesItem(true, overgrow, 1);
        AbilitiesItem abilityTwo = new AbilitiesItem(true, chlorophyll, 2);
        abilities.add(abilityOne);
        abilities.add(abilityTwo);

        List<TypesItem> types = new ArrayList<>();
        TypesItem grass = new TypesItem(1, new Type("grass", "https://pokeapi.co/api/v2/type/12/"));
        TypesItem poison = new TypesItem(2, new Type("poison", "https://pokeapi.co/api/v2/type/4/"));
        types.add(grass);
        types.add(poison);

        List<FormsItem> forms = new ArrayList<>(Collections.singletonList(new FormsItem("bulbasaur", "https://pokeapi.co/api/v2/pokemon-form/1/")));

        StatsItem hp = new StatsItem(new Stat("hp", "hpUrl"), 50, 50);
        StatsItem attack = new StatsItem(new Stat("attack", "attackUrl"), 10, 10);
        StatsItem defense = new StatsItem(new Stat("defense", "defenseUrl"), 20, 20);
        StatsItem specialAttack = new StatsItem(new Stat("specialAttack", "specialAttackUrl"), 30, 30);
        StatsItem specialDefense = new StatsItem(new Stat("specialDefense", "specialDefenseUrl"), 40, 40);
        StatsItem speed = new StatsItem(new Stat("speed", "speedUrl"), 20, 20);
        List<StatsItem> stats = new ArrayList<>(Arrays.asList(hp, attack, defense, specialAttack, specialDefense, speed));

        Pokemon bulbasaur = Pokemon.builder()
                .id(1)
                .baseExperience(60)
                .height(10)
                .isDefault(true)
                .locationAreaEncounters("https://pokeapi.co/api/v2/pokemon/1/encounters")
                .name("bulbasaur")
                .abilities(abilities)
                .forms(forms)
                .order(1)
                .types(types)
                .species(Species.builder().name("bulbasaur").build())
                .stats(stats)
                .weight(20)
                .build();

        Pokemon pikachu = Pokemon.builder()
                .id(2)
                .baseExperience(60)
                .height(10)
                .isDefault(true)
                .locationAreaEncounters("https://pokeapi.co/api/v2/pokemon/3/encounters")
                .name("pikachu")
                .abilities(abilities)
                .forms(forms)
                .order(2)
                .types(types)
                .species(Species.builder().name("pikachu").build())
                .stats(stats)
                .weight(50)
                .build();

        pokemonRepository.save(bulbasaur);
        pokemonRepository.save(pikachu);
    }

    @Test
    void getTypesFetchesProperly() {
        List<Type> types = pokemonRepository.getTypes();
        assertThat(types)
                .hasSize(2)
                .anyMatch(type -> type.getName().equals("poison"));
    }

    @Test
    void getAbilitiesFetchesProperly() {
        List<Ability> abilities = pokemonRepository.getAbilities();
        assertThat(abilities)
                .hasSize(2)
                .anyMatch(ability -> ability.getName().equals("overgrow"));
    }

    @Test
    void getPokemonsByTypeFetchesProperly() {
        Type type = new Type("grass", "https://pokeapi.co/api/v2/type/12/");
        List<Pokemon> pokemons = pokemonRepository.getPokemonsByType(type);
        assertThat(pokemons).hasSize(2);
    }

    @Test
    void getPokemonsByAbilityFetchesProperly() {
        Ability ability = new Ability("overgrow", "https://pokeapi.co/api/v2/ability/65/");
        List<Pokemon> pokemons = pokemonRepository.getPokemonsByAbility(ability);
        assertThat(pokemons).hasSize(2);
    }
}