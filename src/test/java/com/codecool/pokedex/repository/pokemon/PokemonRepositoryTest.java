package com.codecool.pokedex.repository.pokemon;

import com.codecool.pokedex.model.pokemon.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@ActiveProfiles("test")
class PokemonRepositoryTest {

    @Autowired
    private PokemonRepository pokemonRepository;

    @BeforeEach
    public void setUp() {
        List<AbilitiesItem> abilitiesOne = new ArrayList<>();
        Ability overgrow = new Ability("overgrow", "https://pokeapi.co/api/v2/ability/65/");
        Ability chlorophyll = new Ability("chlorophyll", "https://pokeapi.co/api/v2/ability/34/");
        AbilitiesItem abilityOne = new AbilitiesItem(true, overgrow, 1);
        AbilitiesItem abilityTwo = new AbilitiesItem(true, chlorophyll, 2);
        abilitiesOne.add(abilityOne);
        abilitiesOne.add(abilityTwo);

        List<TypesItem> typesOne = new ArrayList<>();
        TypesItem grass = new TypesItem(1, new Type("grass", "https://pokeapi.co/api/v2/type/12/"));
        TypesItem poison = new TypesItem(2, new Type("poison", "https://pokeapi.co/api/v2/type/4/"));
        typesOne.add(grass);
        typesOne.add(poison);

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
                .abilities(abilitiesOne)
                .forms(forms)
                .order(1)
                .types(typesOne)
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
                .abilities(abilitiesOne)
                .forms(forms)
                .order(2)
                .types(typesOne)
                .species(Species.builder().name("pikachu").build())
                .stats(stats)
                .weight(50)
                .build();

        List<AbilitiesItem> abilitiesTwo = new ArrayList<>();
        Ability blaze = new Ability("blaze", "https://pokeapi.co/api/v2/ability/30/");
        Ability solarPower = new Ability("solar power", "https://pokeapi.co/api/v2/ability/22/");
        AbilitiesItem abilityThree = new AbilitiesItem(true, blaze, 1);
        AbilitiesItem abilityFour = new AbilitiesItem(true, solarPower, 2);
        abilitiesOne.add(abilityThree);
        abilitiesOne.add(abilityFour);

        List<TypesItem> typesTwo = new ArrayList<>();
        TypesItem flying = new TypesItem(3, new Type("flying", "https://pokeapi.co/api/v2/type/17/"));
        TypesItem water = new TypesItem(4, new Type("water", "https://pokeapi.co/api/v2/type/40/"));
        typesOne.add(flying);
        typesOne.add(water);

        Pokemon charizard = Pokemon.builder()
                .id(3)
                .baseExperience(100)
                .height(20)
                .isDefault(true)
                .locationAreaEncounters("https://pokeapi.co/api/v2/pokemon/10/encounters")
                .name("charizard")
                .abilities(abilitiesTwo)
                .forms(forms)
                .order(3)
                .types(typesTwo)
                .species(Species.builder().name("charizard").build())
                .stats(stats)
                .weight(70)
                .build();

        pokemonRepository.save(bulbasaur);
        pokemonRepository.save(pikachu);
        pokemonRepository.save(charizard);
    }

    @Test
    void addValidPokemon() {
        Pokemon validPokemom = new Pokemon();
        pokemonRepository.save(validPokemom);

        int size = pokemonRepository.findAll().size();
        int actualSize = 4;
        assertThat(size).isEqualTo(actualSize);
    }

    @Test
    void addInValidPokemon() {
        Pokemon invalidPokemon = null;
        assertThrows(InvalidDataAccessApiUsageException.class, () -> pokemonRepository.save(invalidPokemon));
    }

    @Test
    void getEveryPokemon() {
        List<Pokemon> pokemons = pokemonRepository.findAll();
        assertThat(pokemons).hasSize(3);
    }

    @Test
    void getTypesFetchesProperly() {
        List<Type> types = pokemonRepository.getTypes();
        assertThat(types).hasSize(4);
    }

    @Test
    void getAbilitiesFetchesProperly() {
        List<Ability> abilities = pokemonRepository.getAbilities();
        assertThat(abilities).hasSize(4);
    }

    @Test
    void getPokemonsByValidType() {
        Type type = new Type("grass", "https://pokeapi.co/api/v2/type/12/");
        List<Pokemon> pokemons = pokemonRepository.getPokemonsByType(type);
        assertThat(pokemons).hasSize(2);
    }

    @Test
    void getPokemonsByInvalidType() {
        Type type = new Type("invalid", "invalid");
        List<Pokemon> pokemons = pokemonRepository.getPokemonsByType(type);
        assertThat(pokemons).hasSize(0);
    }

    @Test
    void getPokemonsByValidAbility() {
        Ability ability = new Ability("overgrow", "https://pokeapi.co/api/v2/ability/65/");
        List<Pokemon> pokemons = pokemonRepository.getPokemonsByAbility(ability);
        assertThat(pokemons).hasSize(2);
    }

    @Test
    void getPokemonsByInvalidAbility() {
        Ability ability = new Ability("invalid", "invalid");
        List<Pokemon> pokemons = pokemonRepository.getPokemonsByAbility(ability);
        assertThat(pokemons).hasSize(0);
    }

    @Test
    void getPokemonByValidId() {
        int validId = 1;
        Optional<Pokemon> pokemon = pokemonRepository.findById(validId);
        assertThat(pokemon).isPresent();
    }

    @Test
    void getPokemonByInvalidId() {
        int invalidId = 10;
        Optional<Pokemon> pokemon = pokemonRepository.findById(invalidId);
        assertThat(pokemon).isEmpty();
    }
}